package com.example.common.sysmgr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.sysmgr.entity.RoleAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rdl
 * @version V1.0
 * @ClassName RoleAuthorityMapper
 * @Description TODO 权限表 Mapper 接口
 * @date 2019/10/9 18:49
 **/
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {
    /**
     * 根据角色Id删除
     * @param role
     * @return
     */
    int deleteAuthByRoleId(RoleAuthority role);

    /**
     * 批量插入
     * @param roleAuthList
     */
    void batchInsert(List<RoleAuthority> roleAuthList);

    /**
     * 根据角色Id获取权限
     * @param roleId
     * @return
     */
    List<Long> selectAuthByRoleId(@Param(value = "roleId") Long roleId);

}
