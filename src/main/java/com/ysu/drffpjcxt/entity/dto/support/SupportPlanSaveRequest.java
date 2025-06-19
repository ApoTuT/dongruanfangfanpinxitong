package com.ysu.drffpjcxt.entity.dto.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "新增或更新帮扶计划的请求体")
public class SupportPlanSaveRequest {

    @ApiModelProperty(value = "关联的农户档案ID", required = true)
    private Long farmerId;

    @ApiModelProperty(value = "计划名称", required = true)
    private String planName;

    @ApiModelProperty(value = "主要帮扶目标")
    private String mainGoal;

    @ApiModelProperty(value = "计划开始日期")
    private Date startDate;

    @ApiModelProperty(value = "计划结束日期")
    private Date endDate;

    @ApiModelProperty(value = "计划包含的所有具体帮扶措施列表", required = true)
    private List<SupportMeasureDTO> measures;
}