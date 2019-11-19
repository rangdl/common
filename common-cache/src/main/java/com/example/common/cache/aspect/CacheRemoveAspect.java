package com.example.common.cache.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName CacheRemoveAspect
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/18 14:14
 * @Version 1.0
 **/
@Aspect
@Component
public class CacheRemoveAspect {
    private Logger logger = LoggerFactory.getLogger(CacheRemoveAspect.class);

    @Pointcut(value = "(execution(* *.*(..)) && @annotation(com.example.common.cache.aspect.CacheRemove))")
    private void pointcut() {}

    @AfterReturning(value = "pointcut()")
    private void process(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheRemove cacheRemove = method.getAnnotation(CacheRemove.class);

        Object[] args = joinPoint.getArgs();

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String[] paramNames = {};
        for(Method m:methods){
            if(m.getName().equals(methodName)){
                paramNames = getParamterNames(method);
            }
        }

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        for(int i=0;i<args.length;i++){
            context.setVariable(paramNames[i],args[i]);
        }

        String value = cacheRemove.value();
        boolean allEntries = cacheRemove.allEntries();
        if (allEntries){//清空缓存空间,执行完毕结束方法
            CacheUtils.removeAll(value);
            return;
        }

        String key = cacheRemove.key();
        if (key.length()>0)
            key = parser.parseExpression(key).getValue(context, String.class);

        List<String> keys = new ArrayList<>(cacheRemove.keys().length);
        if (cacheRemove.keys().length>0){
            for (String k:cacheRemove.keys()) {
                if (k.length()>0)
                    keys.add(parser.parseExpression(k).getValue(context, String.class));
            }
        }
        String regex = cacheRemove.keyRegex();
        if (regex.length()>0)
            regex = parser.parseExpression(regex).getValue(context, String.class);

        //正则匹配 将null值替换为规则 ".*?"
        regex = regex.replaceAll("null",".*?");

        if (!key.equals("")){
            removeCache(value,key);
        }

        if (keys.size()>0){
            for (String k:keys) {
                removeCache(value,k);
            }
        }

        if (!regex.equals("")){
            List cacheKeys = CacheUtils.cacheKeys(value);
            Pattern pattern = Pattern.compile(regex);
            for (Object cacheKey: cacheKeys) {
                String cacheKeyStr = String.valueOf(cacheKey);
                if (pattern.matcher(cacheKeyStr).find()){
                    removeCache(value, cacheKeyStr);
                }
            }
        }

    }

    public String[] getParamterNames(Method method){
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        return  u.getParameterNames(method);
    }
    public void removeCache(String value,String key){
        if (CacheUtils.remove(value,key))
            logger.debug("Cache deleted -> key = {}", key);
        else logger.debug("Cache does not exist, not deleted -> key = {}", key);
    }
}
