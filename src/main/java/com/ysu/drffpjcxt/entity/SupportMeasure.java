package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 帮扶措施表(SupportMeasure)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Data
public class SupportMeasure implements Serializable {
    private static final long serialVersionUID = -47544479756047489L;

    /**
     * 措施ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联帮扶计划ID
     * 【已修改】Object -> Long
     */
    private Long planId;

    /**
     * 措施类型
     */
    private String measureType;

    /**
     * 措施内容描述
     */
    private String measureContent;

    /**
     * 责任人用户ID
     * 【已修改】Object -> Long
     */
    private Long responsibleId;

    /**
     * 预算金额
     * 【已修改】Double -> BigDecimal，用于精确计算
     */
    private BigDecimal budgetAmount;

    /**
     * 执行状态
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