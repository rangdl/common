package com.example.common.sysmgr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.sysmgr.entity.User;
import com.example.common.sysmgr.vo.Result;
import com.example.common.sysmgr.vo.UserPassword;
import com.example.common.sysmgr.vo.UserRoleVo;
import com.example.common.sysmgr.vo.UserVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:14
 * @Version 1.0
 **/
public interface UserService extends IService<User> {
    /**
     * 根据用户账号查询用户详情
     * @param account
     * @return
     */
    User findUserByAccount(String account);

    /**
     * 用户登录
     * @param user
     * @return
     */
    Result login(UserVo user, HttpServletResponse response);

    /**
     * ERP登录
     * @return
     */
    Result loginErp(HttpServletResponse response);

    /**
     * 保存用户
     * @param user
     * @return
     */
    Result persist(User user);

    /**
     * 获取用户ID角色
     * @param userId
     * @return
     */
    Result findUserRole(Long userId);

    /**
     * 修改用户角色
     * @param userRole
     * @return
     */
    Result saveUserRoles(UserRoleVo userRole);

    /**
     * 修改用户密码
     * @param userPassword
     * @return
     */
    Result editPassWord(UserPassword userPassword);

}
