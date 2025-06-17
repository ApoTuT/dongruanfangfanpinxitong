package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 走访记录表(VisitRecord)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
@Data
public class VisitRecord implements Serializable {
    private static final long serialVersionUID = -72968571489750359L;

    /**
     * 走访记录ID
     */
    private Long id;

    /**
     * 关联走访计划ID
     */
    private Long planId;

    /**
     * 被走访农户档案ID
     */
    private Long farmerId;

    /**
     * 执行走访的用户ID
     */
    private Long visitorId;

    /**
     * 实际走访日期
     */
    private Date visitDate;

    /**
     * 走访内容纪要
     */
    private String visitContent;

    /**
     * 发现的新问题
     */
    private String problemsFound;

    /**
     * 农户提出的困难或诉求
     */
    private String farmerRequests;

    /**
     * 后续处理建议
     */
    private String followUpAction;

    /**
     * 记录状态
     */
    private String status;

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