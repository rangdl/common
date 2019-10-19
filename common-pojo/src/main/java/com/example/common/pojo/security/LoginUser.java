package com.example.common.pojo.security;

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

    public Long userId;          // 主键ID
    public String account;      // 账号
    public String name;         // 姓名
    public Long tokenKey;         // 用户token_key

    public LoginUser() {
    }

    public LoginUser(String account) {
        this.account=account;
    }

    public LoginUser(Long userId, String account, String name) {
        this.userId = userId;
        this.account = account;
        this.name = name;
    }
    public LoginUser(Long userId, String account, String name, Long tokenKey) {
        this.userId = userId;
        this.account = account;
        this.name = name;
        this.tokenKey = tokenKey;
    }
}
