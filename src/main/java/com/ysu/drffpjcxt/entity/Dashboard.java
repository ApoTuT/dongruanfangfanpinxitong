package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 仪表盘表(Dashboard)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:18
 */
@Data
public class Dashboard implements Serializable {
    private static final long serialVersionUID = 763892904560662242L;

    /**
     * 仪表盘ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 仪表盘名称
     */
    private String dashboardName;

    /**
     * 仪表盘描述
     */
    private String description;

    /**
     * 创建人用户ID
     * 【已修改】Object -> Long
     */
    private Long createdBy;

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