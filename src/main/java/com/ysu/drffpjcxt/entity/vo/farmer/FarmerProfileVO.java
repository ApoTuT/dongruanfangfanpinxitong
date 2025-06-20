package com.ysu.drffpjcxt.entity.vo.farmer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmerProfileVO {

    /**
     * 农户档案ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String headName;

    /**
     * 身份证号
     */
    private String headIdCard;

    private String addressCode;

    private String addressDetail;

    private String phone;

    private Integer populationCount;

    private Integer monitoringType;

    private Integer riskLevel;

    private String mainRiskTypes;

    private String economicInfoJson;

    private String housingInfoJson;

    private String policyInfoJson;

    private String status;

    private String createdBy;

    private String updatedBy;

    private Date createdTime;

    private Date updatedTime;
}