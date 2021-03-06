package com.example.common.pojo.entity.auth;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName LoginLog
 * @Description TODO 登录日志
 * @Author rdl
 * @Date 2019/10/9 18:40
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_login_log")
public class LoginLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String account;

    /**
     * 访问时间
     */
    private Date loginTime;

    /**
     * 内容
     */
    private String content;

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
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

}
