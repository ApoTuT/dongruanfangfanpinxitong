package com.ysu.drffpjcxt.service.auth;

import com.ysu.drffpjcxt.entity.dto.auth.*;
import com.ysu.drffpjcxt.entity.vo.auth.LoginResponseVO;

import java.security.Principal;

/**
 * @Description: 认证服务接口，负责用户注册和登录。
 * @Author: sheng
 * @Date: 2025-06-16
 */
public interface AuthService {

    /**
     * 处理用户注册请求。
     * @param request 包含用户所有注册信息的请求对象。
     * @throws IllegalArgumentException 如果校验失败（如手机号已存在）。
     */
    void register(UserRegisterRequest request);

    /**
     * 处理用户登录请求。
     * @param request 包含登录凭证的请求对象。
     * @return 登录成功后的响应数据，包含token和用户信息。
     * @throws IllegalArgumentException 如果登录验证失败（如密码错误、账户状态异常）。
     */
    LoginResponseVO login(LoginRequest request);

    /**
     * 【新增】发送密码重置验证码
     * @param phone 用户的手机号
     */
    String sendPasswordResetCode(String phone);

    /**
     * 【新增】根据验证码重置密码
     * @param request 包含手机号、验证码和新密码的请求对象
     */
    void resetPassword(PasswordResetRequest request);

    /**
     * 【新增】已登录用户修改自己的密码
     * @param principal Spring Security提供的对象，用于获取当前登录用户名（手机号）
     * @param request 包含旧密码和新密码的请求对象
     */
    void changePassword(Principal principal, ChangePasswordRequest request);
}