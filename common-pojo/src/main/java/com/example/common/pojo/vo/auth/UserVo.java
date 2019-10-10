package com.example.common.pojo.vo.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserVo
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:19
 * @Version 1.0
 **/
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String avatar;
}
