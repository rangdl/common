package com.example.common.sysmgr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.sysmgr.entity.RoleAuthority;

import java.util.List;

/**
 * @ClassName RoleAuthorityService
 * @Description TODO 角色权限关系表 服务类
 * @Author rdl
 * @Date 2019/10/9 18:51
 * @Version 1.0
 **/
public interface RoleAuthorityService extends IService<RoleAuthority> {
    /**
     * 批量新增
     */
    void batchInsert(List<RoleAuthority> list);


    /**
     * 根据角色删除
     * @param role
     */
    void deleteAuthByRoleId(RoleAuthority role);

    /**
     * 根据角色查询
     * @param roleId
     * @return
     */
    List<Long> selectAuthByRoleId(Long roleId);

    /**
     * 根据用户Id查询
     * @return
     */
    List<RoleAuthority> findByUserId(Long userId);

}
