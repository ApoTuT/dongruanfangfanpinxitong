package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 线索核实记录表(ClueVerification)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:18
 */
@Data
public class ClueVerification implements Serializable {
    private static final long serialVersionUID = 938858938879462119L;

    /**
     * 核实记录ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联线索ID
     * 【已修改】Object -> Long
     */
    private Long clueId;

    /**
     * 核实人用户ID
     * 【已修改】Object -> Long
     */
    private Long verifierId;

    /**
     * 实际核实日期
     */
    private Date verificationDate;

    /**
     * 核实过程描述
     */
    private String processDescription;

    /**
     * 核实结论
     */
    private String conclusion;

    /**
     * 详细情况说明
     */
    private String detailedSituation;

    /**
     * 后续处置建议
     */
    private String suggestion;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 核实报告提交时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}