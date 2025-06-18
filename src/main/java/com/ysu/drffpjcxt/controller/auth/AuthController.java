package com.ysu.drffpjcxt.controller.auth;

import com.ysu.drffpjcxt.entity.dto.auth.ChangePasswordRequest;
import com.ysu.drffpjcxt.entity.dto.auth.LoginRequest;
import com.ysu.drffpjcxt.entity.dto.auth.PasswordResetRequest;
import com.ysu.drffpjcxt.entity.dto.auth.UserRegisterRequest;
import com.ysu.drffpjcxt.service.auth.AuthService;
import com.ysu.drffpjcxt.entity.vo.auth.LoginResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Description: 认证API控制器，提供用户注册和登录接口。
 * @Author: sheng
 * @Date: 2025-06-16
 */
@Api(tags = "认证管理")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 用户注册接口。
     * 根据《需求规约》3.2.1节，新用户提交注册信息后进入待审批状态。
     *
     * @param request 包含用户注册信息的请求体
     * @return 表示操作结果的响应实体
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest request) {
        // 调用服务层处理注册逻辑
        authService.register(request);
        // 返回成功信息
        return ResponseEntity.ok("注册申请已提交，请等待管理员审批！");
    }

    /**
     * 用户登录接口。
     * 根据《需求规约》3.2.2节，验证用户凭证并返回Token。
     *
     * @param request 包含登录凭证（手机号和密码）的请求体
     * @return 成功时返回包含Token和用户信息的响应实体
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseVO> loginUser(@RequestBody LoginRequest request) {
        // 调用服务层处理登录逻辑
        LoginResponseVO response = authService.login(request);
        // 返回包含Token和用户信息的成功响应
        return ResponseEntity.ok(response);
    }

    @ApiOperation("忘记密码 - 发送验证码")
    @PostMapping("/forgot-password/send-code")
    public ResponseEntity<String> sendCode(
            @ApiParam(value = "用户注册的手机号") @RequestParam String phone) {
        String code = authService.sendPasswordResetCode(phone);
        if (code == null) {
            return ResponseEntity.badRequest().body("验证码发送失败，请稍后再试。");
        }
        String responseMessage = String.format(code);
        return ResponseEntity.ok(responseMessage);
    }

    @ApiOperation("忘记密码 - 重置密码")
    @PostMapping("/forgot-password/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok("密码重置成功！");
    }

    @ApiOperation("修改密码 (需要登录)")
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(Principal principal, @RequestBody ChangePasswordRequest request) {
        authService.changePassword(principal, request);
        return ResponseEntity.ok("密码修改成功！");
    }
}