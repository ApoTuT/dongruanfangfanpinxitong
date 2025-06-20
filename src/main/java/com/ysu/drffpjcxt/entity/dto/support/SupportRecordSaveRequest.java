package com.ysu.drffpjcxt.entity.dto.support;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 帮扶记录填报请求对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupportRecordSaveRequest {
    private Long measureId;
    private Long planId;
    private Long farmerId;
    private Long userId;
    private Date activityDate;
    private String activityContent;
    private String resourceInputDescription;
    private BigDecimal financialInputAmount;
    private String progressAndEffect;
    private String problemsEncountered;
}
