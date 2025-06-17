package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 物资申请单表(MaterialApplication)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
@Data
public class MaterialApplication implements Serializable {
    private static final long serialVersionUID = 692199273496042047L;

    /**
     * 申请单ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 申请人用户ID
     * 【已修改】Object -> Long
     */
    private Long applicantId;

    /**
     * 申请物资针对的农户ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 申请理由和用途说明
     */
    private String reason;

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