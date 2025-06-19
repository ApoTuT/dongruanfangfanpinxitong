package com.ysu.drffpjcxt.entity.dto.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "审批操作的请求体")
public class ApprovalRequest {

    @ApiModelProperty(value = "审批是否通过", required = true, example = "true")
    private Boolean approved;

    @ApiModelProperty(value = "审批意见/评论")
    private String comments;
}