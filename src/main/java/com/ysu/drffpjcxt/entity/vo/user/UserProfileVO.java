package com.ysu.drffpjcxt.entity.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户个人中心展示的视图对象 (VO)。
 * 包含所有需要向用户展示的个人信息，隐藏了密码等敏感数据。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户个人资料响应体")
public class UserProfileVO {

    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(value = "员工工号")
    private String employeeId;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "手机号 (登录账号)")
    private String phone;
    @ApiModelProperty(value = "身份证号 (脱敏后)")
    private String idCard;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;
    @ApiModelProperty(value = "所属单位名称")
    private String departmentName;
    @ApiModelProperty(value = "账户状态 (1:启用, 0:禁用, 2:待审批, 3:锁定)")
    private Integer status;
}