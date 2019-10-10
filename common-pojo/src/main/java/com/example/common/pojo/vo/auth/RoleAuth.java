package com.example.common.pojo.vo.auth;

import lombok.Data;

import java.util.Set;

/**
 * @ClassName RoleAuth
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:44
 * @Version 1.0
 **/
@Data
public class RoleAuth {
    private Long roleId;
    private Set<Long> authorityIds;
}
