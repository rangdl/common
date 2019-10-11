package com.example.common.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.mapper.auth.RoleMapper;
import com.example.common.mapper.auth.UserRoleMapper;
import com.example.common.pojo.constant.Constants;
import com.example.common.pojo.constant.enumtype.Enums;
import com.example.common.pojo.entity.auth.Role;
import com.example.common.pojo.entity.auth.RoleAuthority;
import com.example.common.pojo.entity.auth.UserRole;
import com.example.common.pojo.security.UserContext;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.pojo.vo.auth.RoleAuth;
import com.example.common.service.auth.RoleAuthorityService;
import com.example.common.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    protected RoleAuthorityService roleAuthorityService;

    @Autowired
    protected UserRoleMapper userRoleMapper;


    @Override
    public List<Role> findRoleByUserId(Long userId) {
        QueryWrapper<UserRole> userRoleWrapper= new QueryWrapper<>();
        userRoleWrapper.eq("user_id",userId);
        userRoleWrapper.eq("yn_flag","1");
        List<UserRole> userRoleList= userRoleMapper.selectList(userRoleWrapper);

        //查询用户角色
        QueryWrapper<Role> roleWrapper= new QueryWrapper<>();
        userRoleWrapper.eq("yn_flag","1");
        roleWrapper.in("id",userRoleList.stream().map(e -> e.getRoleId()).collect(Collectors.toList()));

        List<Role> roleList= baseMapper.selectList(roleWrapper);
        return roleList;
    }

    @Override
    public ResultVo persist(Role role) {
        Date currentDate= Date.from(Instant.now());
        if(role.getId()!=null){
            role.setEditor(UserContext.getCurrentUser().getAccount());
            role.setModifiedTime(currentDate);
            baseMapper.updateById(role);
        }else{
            role.setYnFlag("1");
            role.setEditor(UserContext.getCurrentUser().getAccount());
            role.setCreator(UserContext.getCurrentUser().getAccount());
            role.setCreatedTime(currentDate);
            role.setModifiedTime(currentDate);
            baseMapper.insert(role);
        }
        return ResultVo.getSuccess();
    }

    /**
     * 修改权限
     * @param roleAuth
     * @return
     */
    @Override
    public ResultVo saveRoleAuths(RoleAuth roleAuth) {
        Date currentDate= Date.from(Instant.now());

        RoleAuthority role= new RoleAuthority();
        role.setRoleId(roleAuth.getRoleId());
        role.setModifiedTime(currentDate);
        roleAuthorityService.deleteAuthByRoleId(role);

        RoleAuthority tempAuth ;
        List<RoleAuthority> authList=new ArrayList<>();
        for(Long authId : roleAuth.getAuthorityIds()){
            tempAuth = new RoleAuthority(roleAuth.getRoleId(),authId);
            tempAuth.setYnFlag("1");
            tempAuth.setEditor(UserContext.getCurrentUser().getAccount());
            tempAuth.setCreator(UserContext.getCurrentUser().getAccount());
            tempAuth.setCreatedTime(currentDate);
            tempAuth.setModifiedTime(currentDate);
            authList.add(tempAuth);
        }
        roleAuthorityService.batchInsert(authList);

        return ResultVo.getSuccess(Enums.ResultEnum.SUCCESS_MODIFY);
    }

    /**
     * 根据角色ID获取权限
     * @param roleId
     * @return
     */
    @Override
    public ResultVo selectAuthByRoleId(Long roleId) {
        List<Long> auths= roleAuthorityService.selectAuthByRoleId(roleId);
        return ResultVo.getSuccess(auths);
    }
}
