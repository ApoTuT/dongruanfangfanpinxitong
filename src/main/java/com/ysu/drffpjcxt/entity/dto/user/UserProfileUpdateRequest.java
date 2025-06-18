package com.ysu.drffpjcxt.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户更新个人基本信息的请求数据传输对象。
 * 只包含用户可以自行修改的字段。
 */
@Data
@ApiModel(description = "用户更新个人资料请求体")
public class UserProfileUpdateRequest {

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

}