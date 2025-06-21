package com.ysu.drffpjcxt.entity.dto.visit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitPlanSaveDTO {

    /**
     * 计划ID (更新时需要)
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
     * 走访农户ID
     */
    private Integer farmerId;
}