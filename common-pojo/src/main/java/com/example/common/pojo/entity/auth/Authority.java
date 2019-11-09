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
 * @ClassName Authority
 * @Description TODO 权限表
 * @Author rdl
 * @Date 2019/10/9 18:54
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_authority")
public class Authority extends BaseEntity<Authority> {
    private static final long serialVersionUID = 1L;

    @TableField("authority_name")
    private String name;

    private String code;

    @TableField("full_id")
    private String fullId;

    private String desc;

    private Integer sort;

    private Long pid;

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

}
