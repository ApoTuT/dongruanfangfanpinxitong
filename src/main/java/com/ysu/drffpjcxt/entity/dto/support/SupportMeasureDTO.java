package com.ysu.drffpjcxt.entity.dto.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
@ApiModel(description = "帮扶计划中单个措施的数据传输对象")
public class SupportMeasureDTO {

    @ApiModelProperty(value = "措施类型 (例如: '产业帮扶', '就业帮扶')")
    private String measureType;

    @ApiModelProperty(value = "具体措施内容描述")
    private String measureContent;

    @ApiModelProperty(value = "责任人用户ID")
    private Long responsibleId;

    @ApiModelProperty(value = "预算金额")
    private BigDecimal budgetAmount;
}