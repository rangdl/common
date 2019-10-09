package com.example.common.sysmgr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.sysmgr.entity.Authority;
import com.example.common.sysmgr.vo.AuthorityNode;
import com.example.common.sysmgr.vo.Result;

import java.util.List;

/**
 * @ClassName AuthorityService
 * @Description TODO 权限表 服务类
 * @Author rdl
 * @Date 2019/10/9 18:54
 * @Version 1.0
 **/
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
    Result persist(Authority resource);
}
