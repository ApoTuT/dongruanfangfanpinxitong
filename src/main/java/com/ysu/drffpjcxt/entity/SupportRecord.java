package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 帮扶记录表(SupportRecord)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
@Data
public class SupportRecord implements Serializable {
    private static final long serialVersionUID = 549379777094205476L;

    /**
     * 帮扶记录ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联帮扶措施ID
     * 【已修改】Object -> Long
     */
    private Long measureId;

    /**
     * 关联帮扶计划ID
     * 【已修改】Object -> Long
     */
    private Long planId;

    /**
     * 关联农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 帮扶活动日期
     */
    private Date activityDate;

    /**
     * 活动内容纪实
     */
    private String activityContent;

    /**
     * 资源投入描述
     */
    private String resourceInputDescription;

    /**
     * 投入资金金额
     * 【已修改】Double -> BigDecimal，用于精确计算
     */
    private BigDecimal financialInputAmount;

    /**
     * 进展与成效
     */
    private String progressAndEffect;

    /**
     * 遇到的困难和问题
     */
    private String problemsEncountered;

    /**
     * 记录填报人用户ID
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