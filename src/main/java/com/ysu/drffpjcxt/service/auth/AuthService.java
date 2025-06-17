package com.ysu.drffpjcxt.service.auth;

import com.ysu.drffpjcxt.entity.dto.LoginRequest;
import com.ysu.drffpjcxt.entity.dto.UserRegisterRequest;
import com.ysu.drffpjcxt.entity.vo.LoginResponseVO;

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
}