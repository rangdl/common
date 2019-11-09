package com.example.common.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.common.utils.security.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 8:58
 * @Version 1.0
 **/
@Component
public class JwtUtils {
    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private static JwtUtils jwtUtils;

    @PostConstruct
    public void init() {
        jwtUtils = this;
        jwtUtils.jwtProperties = this.jwtProperties;
    }

    /**
     * 校验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        String secret = getClaim(token, SecurityConsts.ACCOUNT) + jwtUtils.jwtProperties.secretKey;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        verifier.verify(token);
        return true;
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @return
     */
    public static Long getClaimId(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param loginUser
     * @param currentTimeMillis
     * @return
     */
//    public static String sign(Long id, String account, String name, Long tokenKey, String currentTimeMillis) {
    public static String sign(LoginUser loginUser,String currentTimeMillis) {
        // 帐号加JWT私钥加密
        String secret = loginUser.getAccount() + jwtUtils.jwtProperties.getSecretKey();
        // 此处过期时间，单位：毫秒
        Date date = new Date(System.currentTimeMillis() + jwtUtils.jwtProperties.getTokenExpireTime()*60*1000l);
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim(SecurityConsts.USER_ID, loginUser.getUserId())
                .withClaim(SecurityConsts.ACCOUNT, loginUser.getAccount())
                .withClaim(SecurityConsts.USER_NAME, loginUser.getUsername())
                .withClaim(SecurityConsts.TOKEN_KEY, loginUser.getTokenKey())
                .withClaim(SecurityConsts.CURRENT_TIME_MILLIS, currentTimeMillis)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
