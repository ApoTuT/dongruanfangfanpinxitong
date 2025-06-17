package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 物资发放记录表(MaterialDistribution)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
@Data
public class MaterialDistribution implements Serializable {
    private static final long serialVersionUID = 303597596548878765L;

    /**
     * 发放记录ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联物资申请单ID
     * 【已修改】Object -> Long
     */
    private Long applicationId;

    /**
     * 实际领取人姓名
     */
    private String recipientName;

    /**
     * 签字确认图片URL
     */
    private String signatureUrl;

    /**
     * 实际发放时间
     */
    private Date distributionTime;

    /**
     * 发放经办人用户ID
     * 【已修改】Object -> Long
     */
    private Long distributedBy;

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