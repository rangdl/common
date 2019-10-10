package com.example.common.pojo.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO 用户表
 * @Author rdl
 * @Date 2019/10/10 14:28
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_user")
public class User extends BaseEntity<User> {
    private static final long serialVersionUID = 1L;

    private String account;

    private String name;

    private String password;

    private String email;

    @TableField("last_pwd_modified_time")
    private Date lastPwdModifiedTime;

    private String state;

    @TableField("erp_flag")
    private String erpFlag;

    /**
     * 有效标志
     */
    @TableField("yn_flag")
    private String ynFlag;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 修改时间
     */
    @TableField("modified_time")
    private Date modifiedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
