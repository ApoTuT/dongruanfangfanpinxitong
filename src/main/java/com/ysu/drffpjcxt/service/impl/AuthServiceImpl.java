package com.ysu.drffpjcxt.service.impl;

import com.ysu.drffpjcxt.entity.dto.LoginRequest;
import com.ysu.drffpjcxt.entity.dto.UserRegisterRequest;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.exception.auth.AuthException;
import com.ysu.drffpjcxt.exception.auth.RegistrationException;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.service.auth.AuthService;
import com.ysu.drffpjcxt.entity.vo.LoginResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterRequest request) {
        // 使用if判断并抛出自定义异常，替代Assert
        if (request.getPhone() == null || request.getPhone().isEmpty()) {
            throw new RegistrationException("手机号不能为空");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RegistrationException("密码不能为空");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RegistrationException("两次输入的密码不一致");
        }
        if (userMapper.findByPhone(request.getPhone()) != null) {
            throw new RegistrationException("该手机号已被注册");
        }
        if (request.getIdCard() != null && userMapper.findByIdCard(request.getIdCard()) != null) {
            throw new RegistrationException("该身份证号已被注册");
        }

        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(2);
        user.setIsDeleted(false);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        userMapper.insert(user);
    }

    @Override
    public LoginResponseVO login(LoginRequest request) {
        User user = userMapper.findByPhone(request.getPhone());
        if (user == null) {
            throw new AuthException("用户名或密码错误");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // 可以在此处增加登录失败次数记录的逻辑
            throw new AuthException("用户名或密码错误");
        }

        // 检查账户状态并抛出相应的异常
        validateUserStatus(user);

        String token = "dummy-jwt-for-" + user.getPhone() + System.currentTimeMillis();
        List<String> roles = findUserRoles(user.getId());
        List<String> permissions = findUserPermissions(user.getId());

        return buildLoginResponse(user, token, roles, permissions);
    }

    private void validateUserStatus(User user) {
        switch (user.getStatus()) {
            case 0:
                throw new AuthException("账户已被禁用，请联系管理员");
            case 2:
                throw new AuthException("账户正在审批中，请耐心等待");
            case 3:
                throw new AuthException("账户已被锁定，请稍后再试或联系管理员");
        }
    }

    // ... 其他辅助方法 buildLoginResponse, findUserRoles, findUserPermissions ...
    private LoginResponseVO buildLoginResponse(User user, String token, List<String> roles, List<String> permissions) {
        LoginResponseVO.UserInfo userInfo = new LoginResponseVO.UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setRealName(user.getRealName());
        userInfo.setPhone(user.getPhone());
        userInfo.setAvatarUrl(user.getAvatarUrl());
        userInfo.setDepartmentName(user.getDepartmentName());
        userInfo.setRoles(roles);
        userInfo.setPermissions(permissions);
        return new LoginResponseVO(token, userInfo);
    }
    private List<String> findUserRoles(Long userId) {
        // TODO: 实现真实的数据库查询逻辑
        return Collections.singletonList("普通帮扶干部");
    }
    private List<String> findUserPermissions(Long userId) {
        // TODO: 实现真实的数据库查询逻辑
        return Collections.singletonList("profile:view");
    }
}
