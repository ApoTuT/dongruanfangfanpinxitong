package com.ysu.drffpjcxt.service.impl.auth;

import com.ysu.drffpjcxt.common.JwtUtil;
import com.ysu.drffpjcxt.entity.dto.auth.*;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.redis.IRedisService;
import com.ysu.drffpjcxt.exception.auth.AuthException;
import com.ysu.drffpjcxt.exception.auth.RegistrationException;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.service.auth.AuthService;
import com.ysu.drffpjcxt.entity.vo.auth.LoginResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * Redis中存储密码重置验证码的键的前缀。
     */
    private static final String RESET_CODE_PREFIX = "password:reset:";

    /**
     * 密码重置验证码的有效期（分钟）。
     */
    private static final long RESET_CODE_EXPIRY_MINUTES = 5;

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

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPassword(),
                new ArrayList<>() // 权限列表，暂时为空
        );

        // 使用 JwtUtil 生成一个真实的JWT
        String token = jwtUtil.generateToken(userDetails);
        // -- 【核心修正】结束 --

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

    @Override
    public String sendPasswordResetCode(String phone) {
        if (!StringUtils.hasText(phone)) {
            throw new AuthException("手机号不能为空");
        }
        User user = userMapper.findByPhone(phone);
        if (user == null) {
            throw new AuthException("该手机号未注册");
        }

        String code = String.format("%06d", new Random().nextInt(999999));
        String redisKey = RESET_CODE_PREFIX + phone;

        // 【修改】调用新的 redisService.setValue 方法
        // 将过期时间从分钟转换为毫秒
        long expiryInMillis = TimeUnit.MINUTES.toMillis(RESET_CODE_EXPIRY_MINUTES);
        redisService.setValue(redisKey, code, expiryInMillis);

        // **模拟发送短信**
        log.warn("【模拟短信发送】发送密码重置验证码到 {}: {}", phone, code);
        return code;
    }

    @Override
    public void resetPassword(PasswordResetRequest request) {
        String redisKey = RESET_CODE_PREFIX + request.getPhone();

        // 【修改】调用新的 redisService.getValue 方法
        String storedCode = redisService.getValue(redisKey);

        if (!StringUtils.hasText(storedCode)) {
            throw new AuthException("验证码已过期，请重新发送");
        }
        if (!storedCode.equals(request.getCode())) {
            throw new AuthException("验证码不正确");
        }

        User user = userMapper.findByPhone(request.getPhone());
        if (user == null) {
            throw new AuthException("用户不存在");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdateTime(new Date());
        userMapper.update(user);

        // 【修改】调用新的 redisService.remove 方法
        redisService.remove(redisKey);
        log.info("用户密码重置成功，手机号: {}", request.getPhone());
    }

    @Override
    public void changePassword(Principal principal, ChangePasswordRequest request) {
        String phone = principal.getName();
        User user = userMapper.findByPhone(phone);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AuthException("旧密码不正确");
        }
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new AuthException("两次输入的新密码不一致");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdateTime(new Date());
        userMapper.update(user);
        log.info("用户密码修改成功，手机号: {}", phone);
    }
}
