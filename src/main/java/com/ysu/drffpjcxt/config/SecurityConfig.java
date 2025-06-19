package com.ysu.drffpjcxt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置类
 * 用于配置URL授权规则、认证过滤器及其他安全相关的核心组件。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 定义密码编码器，用于密码的加密与验证。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 定义认证提供者，它会使用 UserDetailsService 和 PasswordEncoder 来验证用户信息。
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * 定义认证管理器，这是进行用户认证的核心接口。
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * 配置安全过滤器链，定义所有HTTP请求的安全处理规则。
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭CSRF保护，因为我们使用无状态的JWT认证。
                .csrf().disable()
                // 配置URL的授权规则。
                .authorizeRequests(authorize -> authorize
                        // 对所有以 "/api/auth/" 开头的URL（如注册、登录、忘记密码）允许匿名访问。
                        .antMatchers("/api/auth/**").permitAll()
                        // 除了上述放行的URL外，其他所有请求都必须经过认证。
                        .anyRequest().authenticated()
                )
                // 设置会话管理策略为“无状态”，服务器不创建或使用Session。
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 注册我们自定义的认证提供者。
                .authenticationProvider(authenticationProvider())
                // **关键步骤**：将我们的JWT过滤器添加到过滤器链中，
                // 确保它在标准的用户名密码认证过滤器之前执行。
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}