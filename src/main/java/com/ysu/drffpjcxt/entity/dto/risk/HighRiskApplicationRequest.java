package com.ysu.drffpjcxt.entity.dto.risk;

import com.ysu.drffpjcxt.entity.ApplicationAttachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 高风险标记申请请求 DTO
 * @author sheng
 * @date 2025-06-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighRiskApplicationRequest {

    /**
     * 农户ID
     */
    private Integer farmerId;

    private String status;

    private String riskCauseDescription;

    private String riskTrendDescription;

    private String monitoringStatus;

    private Date riskIdentifiedDate;

    private Date lastWarningTime;

    private Integer totalWarningCount;
}