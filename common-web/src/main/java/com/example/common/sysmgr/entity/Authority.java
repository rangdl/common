package com.example.common.sysmgr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.sysmgr.BaseEntity;
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

    private String name;

    private String code;

    @TableField("full_id")
    private String fullId;

    @TableField("authority_desc")
    private String authorityDesc;

    @TableField("show_order")
    private Integer showOrder;

    private Long pid;

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
