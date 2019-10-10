package com.example.common.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.pojo.entity.auth.Authority;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.pojo.vo.auth.AuthorityNode;

import java.util.List;

/**
 * 权限表 服务类
 */
public interface AuthorityService extends IService<Authority> {
    /**
     * 查询列表
     * @return
     */
    List<AuthorityNode> findAll();

    /**
     * 根据用户查询
     * @param userId
     * @return
     */
    List<Object> findByUserId(Long userId);

    /**
     * 保存
     * @param resource
     * @return
     */
    ResultVo persist(Authority resource);
}
