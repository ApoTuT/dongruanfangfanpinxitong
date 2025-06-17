package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 帮扶结对关系表(Pairing)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:23
 */
@Data
public class Pairing implements Serializable {
    private static final long serialVersionUID = -57636617565477964L;

    /**
     * 结对关系ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 帮扶干部用户ID
     * 【已修改】Object -> Long
     */
    private Long userId;

    /**
     * 结对开始日期
     */
    private Date startDate;

    /**
     * 结对结束日期
     */
    private Date endDate;

    /**
     * 结对状态:0-已解除,1-有效
     */
    private Boolean status;

    /**
     * 调整或解除原因
     */
    private String reasonForChange;

    /**
     * 创建人用户ID
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