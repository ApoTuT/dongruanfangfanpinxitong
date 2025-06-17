package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 审批记录表(ApprovalRecord)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:17
 */
@Data
public class ApprovalRecord implements Serializable {
    private static final long serialVersionUID = -21425814485629333L;

    /**
     * 审批记录ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务ID
     * 【已修改】Object -> Long
     */
    private Long businessId;

    /**
     * 审批步骤名称
     */
    private String stepName;

    /**
     * 审批人用户ID
     * 【已修改】Object -> Long
     */
    private Long approverId;

    /**
     * 审批结果:1-通过,2-驳回,3-退回修改
     * 【已修改】Boolean -> Integer
     */
    private Integer result;

    /**
     * 审批意见
     */
    private String comments;

    /**
     * 审批时间
     */
    private Date approvalTime;

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