package com.ysu.drffpjcxt.entity.vo.risk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 高风险标记申请请求 VO
 * @author sheng
 * @date 2025-06-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighRiskApplicationVo {
    private Long id;
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

    private Date createTime;

    private Date updateTime;
}
