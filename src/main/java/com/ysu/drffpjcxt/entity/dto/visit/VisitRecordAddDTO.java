package com.ysu.drffpjcxt.entity.dto.visit;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增走访记录DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitRecordAddDTO {

    private Long planId;
    private Long farmerId;
    private Long visitorId;
    private Date visitDate;
    private String visitContent;
    private String problemsFound;
    private String farmerRequests;
    private String followUpAction;
    private String status; // 状态（如：待处理、已处理）
}