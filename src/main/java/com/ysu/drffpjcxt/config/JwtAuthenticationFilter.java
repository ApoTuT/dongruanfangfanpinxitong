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
import java.util.Enumeration;

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

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        log.info("================================================================================");
        log.info("JWT过滤器开始处理请求");
        log.info("请求路径: {} {}", method, requestURI);
        log.info("请求来源: {}", request.getRemoteAddr());
        log.info("User-Agent: {}", request.getHeader("User-Agent"));

        // 打印所有请求头信息
        log.info("所有请求头信息:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            if ("authorization".equalsIgnoreCase(headerName)) {
                // 对于Authorization头，只显示前缀信息，保护token安全
                log.info("{}: {}", headerName, headerValue != null ?
                        (headerValue.length() > 20 ? headerValue.substring(0, 20) + "..." : headerValue) : "null");
            } else {
                log.info("{}: {}", headerName, headerValue);
            }
        }

        // 步骤1：检查Authorization头
        log.info("================================================================================");
        log.info("步骤1: 检查Authorization请求头");

        final String authHeader = request.getHeader("Authorization");
        log.info("Authorization头内容: {}", authHeader != null ?
                (authHeader.length() > 50 ? authHeader.substring(0, 50) + "..." : authHeader) : "null");

        if (authHeader == null) {
            log.warn("Authorization头为空");
            log.info("跳过JWT验证，继续处理下一个过滤器");
            filterChain.doFilter(request, response);
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            log.warn("Authorization头格式错误，未使用'Bearer '前缀");
            log.warn("实际格式: {}", authHeader.substring(0, Math.min(authHeader.length(), 20)));
            log.info("跳过JWT验证，继续处理下一个过滤器");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Authorization头格式正确");

        // 步骤2：提取JWT token
        log.info("================================================================================");
        log.info("步骤2: 提取JWT token");

        final String jwt = authHeader.substring(7);
        log.info("提取的JWT token长度: {}", jwt.length());
        log.info("JWT token前20个字符: {}...", jwt.length() > 20 ? jwt.substring(0, 20) : jwt);
        log.info("JWT token后20个字符: ...{}", jwt.length() > 20 ? jwt.substring(jwt.length() - 20) : jwt);

        // 检查token基本格式（JWT应该有两个点分隔三个部分）
        String[] jwtParts = jwt.split("\\.");
        log.info("JWT token部分数量: {} (标准JWT应该有3个部分)", jwtParts.length);
        if (jwtParts.length == 3) {
            log.info("Header部分长度: {}", jwtParts[0].length());
            log.info("Payload部分长度: {}", jwtParts[1].length());
            log.info("Signature部分长度: {}", jwtParts[2].length());
        } else {
            log.warn("WT token格式可能有问题，部分数量不等于3");
        }

        // 步骤3：解析用户名
        log.info("================================================================================");
        log.info("步骤3: 从JWT中解析用户信息");

        final String userPhone;
        try {
            log.info("开始解析JWT token...");
            userPhone = jwtUtil.extractUsername(jwt);
            log.info("成功从JWT中解析出手机号: {}", userPhone);

            // 尝试获取更多token信息
            try {
                boolean isExpired = jwtUtil.isTokenExpired(jwt);
                log.info("Token过期状态: {}", isExpired ? "已过期" : "未过期");

            } catch (Exception e) {
                log.warn("无法获取token详细信息: {}", e.getMessage());
            }

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.error("JWT token已过期: {}", e.getMessage());
            log.error("过期时间: {}", e.getClaims().getExpiration());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token已过期");
            return;
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            log.error("JWT token格式错误: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token格式错误");
            return;
        } catch (io.jsonwebtoken.SignatureException e) {
            log.error("JWT token签名验证失败: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token签名无效");
            return;
        } catch (Exception e) {
            log.error("JWT解析失败: {}", e.getMessage());
            log.error("异常类型: {}", e.getClass().getSimpleName());
            log.error("异常堆栈: ", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无效的Token");
            return;
        }

        // 步骤4：检查SecurityContext
        log.info("================================================================================");
        log.info("步骤4: 检查SecurityContext状态");

        if (userPhone == null || userPhone.trim().isEmpty()) {
            log.error("从JWT中解析的手机号为空");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token中无用户信息");
            return;
        }

        boolean hasExistingAuth = SecurityContextHolder.getContext().getAuthentication() != null;
        log.info("SecurityContext中是否已有认证信息: {}", hasExistingAuth);

        if (hasExistingAuth) {
            String existingUser = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info("当前已认证用户: {}", existingUser);

            if (userPhone.equals(existingUser)) {
                log.info("当前用户与token中的用户一致，跳过重复认证");
                filterChain.doFilter(request, response);
                return;
            } else {
                log.warn("当前用户与token中的用户不一致，重新认证");
                SecurityContextHolder.clearContext();
            }
        }

        // 步骤5：加载用户详情
        log.info("================================================================================");
        log.info("步骤5: 加载用户详情信息");

        UserDetails userDetails;
        try {
            log.info("开始从UserDetailsService加载用户: {}", userPhone);
            userDetails = this.userDetailsService.loadUserByUsername(userPhone);

            if (userDetails == null) {
                log.error("UserDetailsService返回null，用户不存在: {}", userPhone);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户不存在");
                return;
            }

            log.info("✅ 成功加载用户详情:");

        } catch (Exception e) {
            log.error("加载用户详情失败: {}", e.getMessage());
            log.error("异常类型: {}", e.getClass().getSimpleName());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户信息加载失败");
            return;
        }

        // 步骤6：验证token
        log.info("================================================================================");
        log.info("步骤6: 验证JWT token有效性");

        boolean isTokenValid;
        try {
            log.info("开始验证token与用户信息是否匹配...");
            isTokenValid = jwtUtil.validateToken(jwt, userDetails);
            log.info("Token验证结果: {}", isTokenValid ? "有效" : "无效");

        } catch (Exception e) {
            log.error("Token验证过程中发生异常: {}", e.getMessage());
            log.error("异常类型: {}", e.getClass().getSimpleName());
            isTokenValid = false;
        }

        if (!isTokenValid) {
            log.error("Token验证失败！");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token验证失败");
            return;
        }

        // 步骤7：设置认证信息
        log.info("================================================================================");
        log.info("步骤7: 设置Spring Security认证信息");

        try {
            log.info("创建UsernamePasswordAuthenticationToken...");
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            log.info("设置认证详情...");
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            log.info("将认证信息设置到SecurityContext...");
            SecurityContextHolder.getContext().setAuthentication(authToken);

            log.info("认证成功！用户 '{}' 已通过JWT验证", userDetails.getUsername());
            log.info("用户权限: {}", userDetails.getAuthorities());

        } catch (Exception e) {
            log.error("设置认证信息时发生异常: {}", e.getMessage());
            log.error("异常类型: {}", e.getClass().getSimpleName());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "认证设置失败");
            return;
        }

        // 步骤8：继续过滤链
        log.info("================================================================================");
        log.info("步骤8: 继续执行过滤器链");
        log.info("JWT验证完成，继续处理请求...");

        try {
            filterChain.doFilter(request, response);
            log.info("请求处理完成: {} {}", method, requestURI);
        } catch (Exception e) {
            log.error("过滤器链执行过程中发生异常: {}", e.getMessage());
            log.error("异常类型: {}", e.getClass().getSimpleName());
            throw e;
        }

        log.info("================================================================================");
        log.info("JWT过滤器处理结束: {} {}", method, requestURI);
        log.info("================================================================================");
    }
}