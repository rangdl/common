package com.example.common.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.pojo.entity.auth.User;
import com.example.common.pojo.entity.auth.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rdl
 * @version V1.0
 * @ClassName UserMapper
 * @Description TODO 用户表 Mapper 接口
 * @date 2019/10/10 14:37
 **/
public interface UserMapper extends BaseMapper<User> {
    /**
     * 保存用户
     * @param user
     * @return
     */
    int persist(User user);


    /**
     * 根据用户Id删除角色
     * @param user
     * @return
     */
    int deleteRoleByUserId(UserRole user);

    /**
     * 批量插入
     * @param userRoleList
     */
    void batchInsertUserRole(List<UserRole> userRoleList);

    /**
     * 根据用户Id获取角色
     * @param userId
     * @return
     */
    List<Long> selectRoleByUserId(@Param(value = "userId") Long userId);
    /**
     * 刷新用户tokenKey
     * @param userId
     * @return
     */
    int updateTokenKeyById(@Param(value = "userId") Long userId);
}
