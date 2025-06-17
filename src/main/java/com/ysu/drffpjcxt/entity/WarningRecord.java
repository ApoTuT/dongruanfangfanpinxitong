package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 风险预警记录表(WarningRecord)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:31
 */
@Data
public class WarningRecord implements Serializable {
    private static final long serialVersionUID = -53249845853155812L;

    /**
     * 预警记录ID
     */
    private Long id;

    /**
     * 高风险户档案ID
     */
    private Long highRiskId;

    /**
     * 农户档案ID
     */
    private Long farmerId;

    /**
     * 触发预警的规则描述
     */
    private String triggerRule;

    /**
     * 预警级别
     */
    private String warningLevel;

    /**
     * 预警触发时间
     */
    private Date warningTime;

    /**
     * 处理状态:1-待处理,2-处理中,3-已闭环
     */
    private Integer status; // Changed to String to match VARCHAR in XML

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