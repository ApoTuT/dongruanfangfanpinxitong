package com.ysu.drffpjcxt.exception.auth;

/**
 * @Description: 专用于注册失败时的异常
 * @Author: sheng
 * @Date: 2025-06-17
 */
public class RegistrationException extends AuthException {
    public RegistrationException(String message) {
        super(message);
    }
}