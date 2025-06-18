package com.ysu.drffpjcxt.entity.dto.auth;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 用户注册请求的数据传输对象 (DTO)。
 * @Author: sheng
 * @Date: 2025-06-16
 *
 * 该类严格对应前端注册表单提交的所有字段，
 * 参考了《概要设计说明书》4.1节中的 UserRegisterRequest 定义。
 */
@Data
@ApiModel(description = "用户注册请求体")
public class UserRegisterRequest {

    @ApiModelProperty(value = "员工工号", required = true)
    private String employeeId;

    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;

    @ApiModelProperty(value = "手机号码 (将作为登录账号，必须唯一)", required = true)
    private String phone;

    @ApiModelProperty(value = "登录密码", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码 (用于后端校验)", required = true)
    private String confirmPassword;

    @ApiModelProperty(value = "18位身份证号码", required = true)
    private String idCard;

    @ApiModelProperty(value = "电子邮箱 (可选)")
    private String email;

    @ApiModelProperty(value = "省级行政区划代码")
    private String provinceCode;

    @ApiModelProperty(value = "市级行政区划代码")
    private String cityCode;

    @ApiModelProperty(value = "县级行政区划代码")
    private String countyCode;

    @ApiModelProperty(value = "乡级行政区划代码")
    private String townshipCode;

    @ApiModelProperty(value = "村级行政区划代码")
    private String villageCode;

    @ApiModelProperty(value = "所属单位名称")
    private String departmentName;
}