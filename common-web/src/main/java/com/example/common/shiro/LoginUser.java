package com.example.common.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginUser
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 14:48
 * @Version 1.0
 **/
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long userId;          // 主键ID
    public String account;      // 账号
    public String name;         // 姓名

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
}
