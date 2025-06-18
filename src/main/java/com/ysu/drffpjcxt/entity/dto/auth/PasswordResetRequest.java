package com.ysu.drffpjcxt.entity.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "重置密码请求体")
public class PasswordResetRequest {
    @ApiModelProperty(value = "手机号码", required = true)
    private String phone;

    @ApiModelProperty(value = "收到的6位验证码", required = true)
    private String code;

    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}