package com.example.common.sysmgr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.sysmgr.entity.User;
import com.example.common.sysmgr.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO 用户表 Mapper 接口
 * @Author rdl
 * @Date 2019/10/9 18:36
 * @Version 1.0
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
}
