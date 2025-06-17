package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限表(Permission)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:24
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = -49249082003235442L;

    /**
     * 权限ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限标识符
     */
    private String permissionCode;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 权限描述
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