package com.example.common.pojo.constant.enumtype;

public interface Enums {
    Integer getCode();
    String getDescribe();

    public static <T extends Enums> T getEnum(Integer code , Class<T> enums){
        //循环遍历你的枚举类中的所有code值，相等则返回给调用者
        for (T each:enums.getEnumConstants()) {
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
    public enum  ResultEnum implements Enums {
        _200(20000,"成功!"),
        _200_CHECK(20001,"验证成功!"),
        _200_REQUEST(20002,"请求成功!"),
        _200_SAVE(20003,"保存成功!"),
        _200_MODIFY(20004,"修改成功!"),
        _200_REMOVE(20005,"删除成功!"),
        _200_LOGIN(20005,"登陆成功!"),

        _400(40000,"错误!"),
        _400_PARAMETERS_MISSING(40010,"参数不完整!"),
        _400_PASSWORD(40011,"密码错误!"),
        _400_PASSWORD_NULL(40012,"密码不能为空!"),
        _400_PASSWORD_PRIMARY(40013,"原密码错误!"),

//        ERROR_NOT_EXIST_USER(40017,"用户不存在!"),
        _400_ACCOUNT_NULL(400015,"账户不能为空!"),
        _400_NOT_EXIST_ACCOUNT(40016,"账户不存在!"),
        _400_EXIST_USER(40017,"账号已经存在!"),
        _400_NOT_ALLOW_MODIFY(40018,"账号不能修改!"),
        _400_LOCKING(40019,"已被锁定!"),

        _401(40100,"权限不足!"),
        _401_TOKEN_CHECK_STALE_DATED(40101,"token过期!"),
        _401_TOKEN_CHECK_ILLEFALITY_ERROR(40102,"非法token!"),
        _401_TOKEN_CHECK_OTHER_LOGIN(40103,"其他客户端登录!"),

        _403(40300,"拒绝访问!"),
        _404(40300,"您所请求的资源无法找到!"),



        _500(50000,"服务端错误!");

//        PARAMETERS_MISSING(50002,"参数不完整!");
//
//        TOKEN_CHECK_ILLEFALITY_ERROR(50008,"非法token!"),
//
//        TOKEN_CHECK_OTHER_LOGIN(50012,"其他客户端登录!"),
//
//        TOKEN_CHECK_STALE_DATED(50014,"token 过期!");


        private ResultEnum(Integer code, String describe) {
            this.code = code;
            this.describe = describe;
        }

        private Integer code;
        private String describe;

        @Override
        public String getDescribe() {
            return describe;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        public String toString(){
            return code+","+describe;
        }

        /**
         * 通过key 查找描述 方法
         *
         * @param key
         *            .
         * @return string 返回对应名称.
         * @author ：jrhelin
         */
        public static String getDescribeByKey(String key) {
            for (ResultEnum pm : ResultEnum.values()) {
                if (pm.getCode().equals(key)) {
                    return pm.getDescribe();
                }
            }
            return null;
        }
    }
}
