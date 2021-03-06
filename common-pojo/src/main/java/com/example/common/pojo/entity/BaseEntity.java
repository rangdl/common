package com.example.common.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/10 14:31
 * @Version 1.0
 **/
public class BaseEntity <T extends Model<?>> extends Model<T> {
    protected Long id;

    public BaseEntity() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
