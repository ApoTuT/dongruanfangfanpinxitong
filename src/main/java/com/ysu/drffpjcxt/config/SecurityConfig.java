package com.ysu.drffpjcxt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Description: Spring Security 配置类
 * @Author: Gemini
 * @Date: 2025-06-17
 *
 * 用于配置哪些URL需要认证，哪些可以匿名访问。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 配置密码编码器，用于对密码进行加密和比对。
     * 我在之前的代码中建议过，这里是具体的实现。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置安全过滤器链，这是Spring Security的核心配置。
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. 关闭CSRF（跨站请求伪造）保护。
            // 因为我们的认证方式是基于JWT的无状态API，所以不需要CSRF保护。
            .csrf().disable()

            // 2. 配置会话管理策略为“无状态”(STATELESS)。
            // 这意味着服务器不会创建或使用HTTP Session，每次请求都是独立的。
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            // 3. 配置URL的授权规则。
            .authorizeRequests(authorize -> authorize
                // 4. 对登录和注册接口允许所有用户访问（包括未认证的匿名用户）。
                // 这是解决您问题的关键！
                .antMatchers("/api/auth/register", "/api/auth/login","/api/auth/forgot-password/**").permitAll()

                // 5. 除了上面明确放行的URL，其他所有请求都必须经过认证才能访问。
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
