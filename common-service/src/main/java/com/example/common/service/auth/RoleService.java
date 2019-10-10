package com.example.common.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.pojo.entity.auth.Role;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.pojo.vo.auth.RoleAuth;

import java.util.List;

/**
 * 角色表 服务类
 */
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
    ResultVo persist(Role role);

    /**
     * 修改权限
     * @param roleAuth
     * @return
     */
    ResultVo saveRoleAuths(RoleAuth roleAuth);

    /**
     * 根据角色ID获取权限
     * @param roleId
     * @return
     */
    ResultVo selectAuthByRoleId(Long roleId);
}
