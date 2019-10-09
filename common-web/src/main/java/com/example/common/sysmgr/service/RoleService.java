package com.example.common.sysmgr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.sysmgr.entity.Role;
import com.example.common.sysmgr.vo.Result;
import com.example.common.sysmgr.vo.RoleAuth;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description TODO 角色表 服务类
 * @Author rdl
 * @Date 2019/10/9 18:43
 * @Version 1.0
 **/
public interface RoleService extends IService<Role> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    Result persist(Role role);

    /**
     * 修改权限
     * @param roleAuth
     * @return
     */
    Result saveRoleAuths(RoleAuth roleAuth);

    /**
     * 根据角色ID获取权限
     * @param roleId
     * @return
     */
    Result selectAuthByRoleId(Long roleId);
}
