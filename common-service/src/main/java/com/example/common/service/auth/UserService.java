package com.example.common.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.pojo.entity.auth.User;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.pojo.vo.auth.UserPassword;
import com.example.common.pojo.vo.auth.UserRoleVo;
import com.example.common.pojo.vo.auth.UserVo;

import javax.servlet.http.HttpServletResponse;

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
    ResultVo login(UserVo user, HttpServletResponse response);

    /**
     * ERP登录
     * @return
     */
    ResultVo loginErp(HttpServletResponse response);

    /**
     * 保存用户
     * @param user
     * @return
     */
    ResultVo persist(User user);

    /**
     * 获取用户ID角色
     * @param userId
     * @return
     */
    ResultVo findUserRole(Long userId);

    /**
     * 修改用户角色
     * @param userRole
     * @return
     */
    ResultVo saveUserRoles(UserRoleVo userRole);

    /**
     * 修改用户密码
     * @param userPassword
     * @return
     */
    ResultVo editPassWord(UserPassword userPassword);

    /**
     * 获取 token_key
     * @return
     */
    long getTokenKey(Long id);

    ResultVo logout(Long id);
}
