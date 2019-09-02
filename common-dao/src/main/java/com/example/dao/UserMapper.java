package com.example.dao;

import org.apache.ibatis.annotations.Mapper;


/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author rdl
 * @Date 2019/8/29 17:22
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {

    String selectUserName(Integer uId);
}
