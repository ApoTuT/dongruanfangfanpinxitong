package com.ysu.drffpjcxt.entity;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关联表(UserRole)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
@Data
@Builder
public class UserRole implements Serializable {
    private static final long serialVersionUID = 341829594630314245L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

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