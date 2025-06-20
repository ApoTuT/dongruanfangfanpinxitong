package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 帮扶计划表(SupportPlan)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Data
public class SupportPlan implements Serializable {
    private static final long serialVersionUID = 807168578180855177L;

    /**
     * 计划ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 主要目标
     */
    private String mainGoal;

    /**
     * 计划开始日期
     */
    private Date startDate;

    /**
     * 计划结束日期
     */
    private Date endDate;

    /**
     * 计划状态
     */
    private String status;

    /**
     * 制定人用户ID
     * 【已修改】Object -> Long
     */
    private Long createdBy;

    /**
     * 制定人真实姓名（非数据库字段，仅用于展示）
     */
    private String creatorName;

    /**
     * 农户姓名（非数据库字段，仅用于展示）
     */
    private String farmerHeadName;

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