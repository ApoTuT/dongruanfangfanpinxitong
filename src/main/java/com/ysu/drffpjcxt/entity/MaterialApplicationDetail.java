package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 物资申请明细表(MaterialApplicationDetail)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
@Data
public class MaterialApplicationDetail implements Serializable {
    private static final long serialVersionUID = 200856672983788293L;

    /**
     * 申请明细ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 物资申请单ID
     * 【已修改】Object -> Long
     */
    private Long applicationId;

    /**
     * 物资信息ID
     * 【已修改】Object -> Long
     */
    private Long materialId;

    /**
     * 申请数量
     * 【已修改】Object -> Integer
     */
    private Integer requestedQuantity;

    /**
     * 批准数量
     * 【已修改】Object -> Integer
     */
    private Integer approvedQuantity;

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