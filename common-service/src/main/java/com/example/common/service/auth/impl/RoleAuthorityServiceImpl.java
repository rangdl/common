package com.example.common.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.mapper.auth.RoleAuthorityMapper;
import com.example.common.mapper.auth.UserRoleMapper;
import com.example.common.pojo.entity.auth.RoleAuthority;
import com.example.common.pojo.entity.auth.UserRole;
import com.example.common.service.auth.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {
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
