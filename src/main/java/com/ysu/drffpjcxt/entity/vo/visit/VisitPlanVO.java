package com.ysu.drffpjcxt.entity.vo.visit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitPlanVO {
    /**
     * 计划ID
     */
    private Integer planId;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 计划描述
     */
    private String description;

    /**
     * 计划开始日期
     */
    private Date startDate;

    /**
     * 计划结束日期
     */
    private Date endDate;

    /**
     * 计划人ID
     */
    private Integer plannerId;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 状态
     */
    private String status;

    /**
     * 走访农户ID
     */
    private Integer farmerId;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;
}