package com.example.common.sysmgr.vo;

import lombok.Data;

import java.util.Set;

/**
 * @ClassName UserRoleVo
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:20
 * @Version 1.0
 **/
@Data
public class UserRoleVo {
    private Long userId;
    private Set<Long> roleIds;
}
