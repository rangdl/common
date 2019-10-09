package com.example.common.sysmgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.sysmgr.entity.RoleAuthority;
import com.example.common.sysmgr.entity.UserRole;
import com.example.common.sysmgr.mapper.RoleAuthorityMapper;
import com.example.common.sysmgr.mapper.UserRoleMapper;
import com.example.common.sysmgr.service.RoleAuthorityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName RoleAuthorityServiceImpl
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:52
 * @Version 1.0
 **/
public class RoleAuthorityServiceImpl  extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {
    @Autowired
    protected UserRoleMapper userRoleMapper;

    /**
     * 批量新增
     * @param list
     */
    @Override
    public void batchInsert(List<RoleAuthority> list) {
        baseMapper.batchInsert(list);
    }

    /**
     * 根据角色删除
     * @param role
     */
    @Override
    public void deleteAuthByRoleId(RoleAuthority role) {
        baseMapper.deleteAuthByRoleId(role);
    }

    /**
     * 根据角色查询
     * @param roleId
     * @return
     */
    @Override
    public List<Long> selectAuthByRoleId(Long roleId) {
        return baseMapper.selectAuthByRoleId(roleId);
    }

    @Override
    public List<RoleAuthority> findByUserId(Long userId) {
        //获取角色
        QueryWrapper<UserRole> userRoleWrapper= new QueryWrapper<>();
        userRoleWrapper.eq("user_id",userId);
        userRoleWrapper.eq("yn_flag","1");
        List<UserRole> userRoleList= userRoleMapper.selectList(userRoleWrapper);

        //获取角色权限
        List<RoleAuthority> roleAuthList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(userRoleList)){
            QueryWrapper<RoleAuthority> roleAuthWrapper= new QueryWrapper<>();
            roleAuthWrapper.eq("yn_flag","1");
            roleAuthWrapper.in("role_id",userRoleList.stream().map(e -> e.getRoleId()).collect(Collectors.toSet()));
            roleAuthList= baseMapper.selectList(roleAuthWrapper);
        }
        return roleAuthList;
    }
}
