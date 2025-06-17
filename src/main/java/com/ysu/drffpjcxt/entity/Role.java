package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 479901298793876140L;

    /**
     * 角色ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}