package com.ysu.drffpjcxt.exception.auth;

/**
 * @Description: 认证过程中发生的异常基类
 * @Author: Gemini
 * @Date: 2025-06-17
 */
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}