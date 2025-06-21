package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 走访计划表(VisitPlan)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
@Data
public class VisitPlan implements Serializable {
    private static final long serialVersionUID = 131620965063795939L;

    /**
     * 走访计划ID
     */
    private Long id;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 计划制定人用户ID
     */
    private Long plannerId;

    /**
     * 计划开始日期
     */
    private Date startDate;

    /**
     * 计划结束日期
     */
    private Date endDate;

    /**
     * 计划描述和走访重点
     */
    private String description;

    /**
     * 计划状态
     */
    private String status;

    /**
     * 走访农户ID
     */
    private Integer farmerId;

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