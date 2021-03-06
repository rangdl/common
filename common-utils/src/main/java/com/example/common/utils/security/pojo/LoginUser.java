package com.example.common.utils.security.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginUser
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 8:43
 * @Version 1.0
 **/
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    public Long userId;
    /**
     *账号
     */
    public String account;
    /**
     * 姓名
     */
    public String username;
    /**
     * 用户token_key
     */
    public Long tokenKey;


    public LoginUser() {
    }

    public LoginUser(String account) {
        this.account=account;
    }

    public LoginUser(Long userId, String account, String username) {
        this.userId = userId;
        this.account = account;
        this.username = username;
    }
    public LoginUser(Long userId, String account, String username, Long tokenKey) {
        this.userId = userId;
        this.account = account;
        this.username = username;
        this.tokenKey = tokenKey;
    }
}
