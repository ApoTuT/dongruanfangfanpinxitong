package com.ysu.drffpjcxt.entity.dto.auth;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 用户登录请求的数据传输对象 (DTO)。
 * @Author: sheng
 * @Date: 2025-06-16
 */
@Data
@ApiModel(description = "用户登录请求体")
public class LoginRequest {
    @ApiModelProperty(value = "手机号码 (登录账号)", required = true)
    private String phone;

    @ApiModelProperty(value = "登录密码", required = true)
    private String password;
}