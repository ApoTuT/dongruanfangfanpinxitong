package com.ysu.drffpjcxt.entity.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "修改密码请求体")
public class ChangePasswordRequest {
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;

    @ApiModelProperty(value = "确认新密码", required = true)
    private String confirmNewPassword;
}