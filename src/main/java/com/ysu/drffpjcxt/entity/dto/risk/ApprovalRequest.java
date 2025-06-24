package com.ysu.drffpjcxt.entity.dto.risk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 审批请求 DTO
 * @author sheng
 * @date 2025-06-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalRequest {

    /**
     * 申请ID
     */
    private Long applicationId;

    /**
     * 审批结果: "通过" 或 "驳回"
     */
    private String result;

    /**
     * 审批意见
     */
    private String comment;

    /**
     * 审批人ID
     */
    private Long approverId;
}