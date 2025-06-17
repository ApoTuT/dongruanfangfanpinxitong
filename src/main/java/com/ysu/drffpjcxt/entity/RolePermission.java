package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限关联表(RolePermission)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -18034983316441668L;

    /**
     * 主键ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 角色ID
     * 【已修改】Object -> Long
     */
    private Long roleId;

    /**
     * 权限ID
     * 【已修改】Object -> Long
     */
    private Long permissionId;

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