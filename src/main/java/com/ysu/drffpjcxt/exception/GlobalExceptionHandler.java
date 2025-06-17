package com.ysu.drffpjcxt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Description: 全局异常处理器。
 * @Author: sheng
 * @Date: 2025-06-17
 * 使用 @RestControllerAdvice 注解，可以捕获整个应用中抛出的异常，
 * 并将它们转换为统一的、对前端友好的JSON格式响应。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 内部类，用于定义标准的错误响应体结构。
     */
    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
    }

    /**
     * 处理认证相关的异常（包括登录和注册失败）。
     * @param ex 捕获到的 AuthException 异常
     * @param request HTTP请求对象
     * @return 包含错误信息的ResponseEntity，HTTP状态码为400 (Bad Request)
     */
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Authentication Error",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理其他所有未被捕获的异常。
     * @param ex 捕获到的 Exception 异常
     * @param request HTTP请求对象
     * @return 包含错误信息的ResponseEntity，HTTP状态码为500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
        // 在生产环境中，您可能不希望将详细的堆栈信息暴露给前端
        // 这里为了调试方便，我们返回异常的原始信息
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "系统内部发生未知错误，请联系管理员。错误详情: " + ex.getMessage(),
                request.getRequestURI()
        );
        // 同时在服务器日志中打印完整的堆栈信息
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
