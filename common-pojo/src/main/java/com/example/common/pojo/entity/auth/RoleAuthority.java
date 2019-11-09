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
 * @ClassName RoleAuthority
 * @Description TODO 角色权限关系表
 * @Author rdl
 * @Date 2019/10/9 18:50
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_role_authority")
public class RoleAuthority extends BaseEntity<RoleAuthority> {
    @TableField("role_id")
    private Long roleId;

    @TableField("authority_id")
    private Long authorityId;

    /**
     * 有效标志
     */
    private Boolean flag;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 修改人
     */
    private Long editor;

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

    public RoleAuthority() {
    }

    public RoleAuthority(Long roleId, Long authorityId) {
        this.roleId = roleId;
        this.authorityId = authorityId;
    }
}
