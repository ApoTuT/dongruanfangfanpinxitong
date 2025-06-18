package com.ysu.drffpjcxt.config;

import com.ysu.drffpjcxt.common.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("====== JWT 过滤器开始处理: {} ======", request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userPhone;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("请求头中无 'Authorization' 或未使用 'Bearer' 前缀，跳过JWT过滤器。");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        log.info("从请求头中提取到JWT。");

        try {
            userPhone = jwtUtil.extractUsername(jwt);
            log.info("从JWT中解析出手机号: {}", userPhone);
        } catch (Exception e) {
            log.error("JWT解析失败: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无效的Token");
            return;
        }

        if (userPhone != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("SecurityContext中无认证信息，开始加载用户信息。");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userPhone);

            if (userDetails != null && jwtUtil.validateToken(jwt, userDetails)) {
                log.info("Token验证成功，为用户 '{}' 创建认证信息。", userDetails.getUsername());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("成功设置SecurityContext，用户已认证。");
            } else {
                log.warn("Token验证失败！");
            }
        } else {
            log.info("手机号为空或SecurityContext中已有认证信息，跳过加载。");
        }

        filterChain.doFilter(request, response);
        log.info("====== JWT 过滤器处理结束: {} ======", request.getRequestURI());
    }
}