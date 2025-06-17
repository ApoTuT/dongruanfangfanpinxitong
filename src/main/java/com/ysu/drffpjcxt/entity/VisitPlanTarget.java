package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 走访计划目标表(VisitPlanTarget)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
@Data
public class VisitPlanTarget implements Serializable {
    private static final long serialVersionUID = 604958392547368458L;

    /**
     * 关联记录ID
     */
    private Long id;

    /**
     * 走访计划ID
     */
    private Long planId;

    /**
     * 目标农户档案ID
     */
    private Long farmerId;

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