package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 贫困线索表(PovertyClue)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:24
 */
@Data
public class PovertyClue implements Serializable {
    private static final long serialVersionUID = -46470155088595624L;

    /**
     * 线索ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 线索来源
     */
    private String clueSource;

    /**
     * 主要风险类型
     */
    private String riskType;

    /**
     * 风险情况描述
     */
    private String riskDescription;

    /**
     * 紧急程度:1-特急,2-紧急,3-一般
     * 【已修改】Boolean -> Integer
     */
    private Integer urgencyLevel;

    /**
     * 发现日期
     */
    private Date discoveryDate;

    /**
     * 处理状态
     */
    private String status;

    /**
     * 分派接收人ID
     * 【已修改】Object -> Long
     */
    private Long assigneeId;

    /**
     * 分派时间
     */
    private Date dispatchTime;

    /**
     * 处理时限
     */
    private Date deadline;

    /**
     * 录入来源描述
     */
    private String entrySourceDescription;

    /**
     * 录入人用户ID
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