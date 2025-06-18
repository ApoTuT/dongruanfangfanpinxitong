package com.ysu.drffpjcxt.entity.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 用户登录成功响应的视图对象 (VO)。
 * @Author: sheng
 * @Date: 2025-06-16
 *
 * 该类用于在用户成功登录后，向前端返回令牌和必要的用户信息。
 * 参考了《概要设计说明书》4.1节中的 LoginResponseVO 定义。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录成功响应体")
public class LoginResponseVO {

    @ApiModelProperty(value = "用于后续请求认证的JSON Web Token (JWT)")
    private String token;

    @ApiModelProperty(value = "登录成功的用户信息详情")
    private UserInfo userInfo;

    /**
     * 内部类，定义了前端需要展示的用户信息结构。
     */
    @Data
    @ApiModel(description = "登录用户信息")
    public static class UserInfo {
        @ApiModelProperty(value = "用户ID")
        private Long userId;
        @ApiModelProperty(value = "真实姓名")
        private String realName;
        @ApiModelProperty(value = "手机号")
        private String phone;
        @ApiModelProperty(value = "头像URL")
        private String avatarUrl;
        @ApiModelProperty(value = "所属单位名称")
        private String departmentName;
        @ApiModelProperty(value = "角色列表")
        private List<String> roles;
        @ApiModelProperty(value = "权限列表")
        private List<String> permissions;
    }
}