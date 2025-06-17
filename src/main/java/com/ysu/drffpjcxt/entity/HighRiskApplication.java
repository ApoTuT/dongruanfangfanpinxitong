package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 高风险标记申请表(HighRiskApplication)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:21
 */
@Data
public class HighRiskApplication implements Serializable {
    private static final long serialVersionUID = 197723621034027594L;

    /**
     * 申请ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 申请人用户ID
     * 【已修改】Object -> Long
     */
    private Long applicantId;

    /**
     * 申请类型:1-新增高风险标记,2-申请解除高风险标记
     * 【已修改】Boolean -> Integer
     */
    private Integer applicationType;

    /**
     * 申请理由和风险描述
     */
    private String reason;

    /**
     * 期望风险等级
     * 【已修改】Boolean -> Integer
     */
    private Integer targetRiskLevel;

    /**
     * 审批状态
     */
    private String status;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 申请提交时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}