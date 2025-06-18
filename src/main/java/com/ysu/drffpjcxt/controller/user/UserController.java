package com.ysu.drffpjcxt.controller.user;

import com.ysu.drffpjcxt.entity.dto.user.UserProfileUpdateRequest;
import com.ysu.drffpjcxt.entity.vo.user.UserProfileVO;
import com.ysu.drffpjcxt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Description: 用户个人信息管理控制器
 * @Author: sheng
 * @Date: 2025-06-18
 */
@Api(tags = "用户个人信息管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * 获取当前登录用户的个人资料。
     * 对应《用例规约》UC_COM_001 的查看个人信息部分。
     * @param principal Spring Security自动注入，包含当前用户信息。
     * @return 用户的个人资料视图对象。
     */
    @ApiOperation("获取当前用户个人资料")
    @GetMapping("/profile")
    public ResponseEntity<UserProfileVO> getUserProfile(Principal principal) {
        log.info("进入 UserController 的 getUserProfile 方法...");
        UserProfileVO userProfile = userService.getUserProfile(principal);
        return ResponseEntity.ok(userProfile);
    }

    /**
     * 更新当前登录用户的个人资料。
     * 对应《用例规约》UC_COM_001 的修改个人信息部分。
     * @param principal Spring Security自动注入。
     * @param request 包含要更新字段的请求体。
     * @return 更新后的用户个人资料。
     */
    @ApiOperation("更新当前用户个人资料")
    @PutMapping("/profile")
    public ResponseEntity<UserProfileVO> updateUserProfile(Principal principal, @RequestBody UserProfileUpdateRequest request) {
        log.info("进入 UserController 的 updateUserProfile 方法...");
        UserProfileVO updatedProfile = userService.updateUserProfile(principal, request);
        return ResponseEntity.ok(updatedProfile);
    }
}