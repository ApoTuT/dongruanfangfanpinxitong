package com.ysu.drffpjcxt.service.Impl;

import com.ysu.drffpjcxt.entity.dto.LoginRequest;
import com.ysu.drffpjcxt.entity.dto.UserRegisterRequest;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.service.auth.AuthService;
import com.ysu.drffpjcxt.entity.vo.LoginResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: 认证服务实现类。
 * @Author: sheng
 * @Date: 2025-06-16
 *
 * 实现了注册和登录的核心业务逻辑。
 * 注意：此实现依赖于 Spring Security 的 PasswordEncoder 和一个 JWT 工具类。
 * 您需要在项目中自行配置这两个Bean。
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    // 假设您已经配置了PasswordEncoder Bean用于密码加密/比对
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // 假设你有一个JWT工具类来生成Token
    // @Autowired
    // private JwtTokenUtil jwtTokenUtil;

    @Override
    public void register(UserRegisterRequest request) {
        // 步骤1: 校验输入数据是否合规
        validateRegistrationRequest(request);

        // 步骤2: 创建用户实体并设置属性
        User user = new User();
        BeanUtils.copyProperties(request, user);

        // 步骤3: 加密密码
        // user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPassword("ENCODED_" + request.getPassword()); // 示例：使用伪加密

        // 步骤4: 设置初始状态和默认值
        // 根据《需求规约》，新注册用户状态为“待审批”(2)
        user.setStatus(2);
        user.setIsDeleted(false);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 步骤5: 将用户信息插入数据库
        userMapper.insert(user);

        // 后续步骤：可以触发一个事件，通知管理员有新的注册申请需要审批
    }

    @Override
    public LoginResponseVO login(LoginRequest request) {
        // 步骤1: 根据手机号查找用户
        User user = userMapper.findByPhone(request.getPhone());
        Assert.notNull(user, "用户不存在或密码错误");

        // 步骤2: 验证密码
        // boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        boolean passwordMatches = ("ENCODED_" + request.getPassword()).equals(user.getPassword()); // 示例：使用伪加密比对
        Assert.isTrue(passwordMatches, "用户不存在或密码错误");

        // 步骤3: 检查账户状态
        validateUserStatus(user);

        // 步骤4: 生成JWT Token
        // String token = jwtTokenUtil.generateToken(user); // 实际应传入user对象或其唯一标识
        String token = "dummy-jwt-for-" + user.getPhone() + System.currentTimeMillis(); // 生成一个示例Token

        // 步骤5: 查询用户角色和权限（此处为模拟，实际应从数据库查询）
        List<String> roles = findUserRoles(user.getId());
        List<String> permissions = findUserPermissions(user.getId());

        // 步骤6: 构造并返回VO对象
        return buildLoginResponse(user, token, roles, permissions);
    }

    /**
     * 校验注册请求的辅助方法。
     */
    private void validateRegistrationRequest(UserRegisterRequest request) {
        Assert.hasText(request.getPhone(), "手机号不能为空");
        Assert.hasText(request.getPassword(), "密码不能为空");
        Assert.hasText(request.getIdCard(), "身份证号不能为空");
        Assert.isTrue(request.getPassword().equals(request.getConfirmPassword()), "两次输入的密码不一致");

        // 检查手机号和身份证号是否已被注册
        Assert.isNull(userMapper.findByPhone(request.getPhone()), "该手机号已被注册");
        Assert.isNull(userMapper.findByIdCard(request.getIdCard()), "该身份证号已被注册");
    }

    /**
     * 校验用户账户状态的辅助方法。
     */
    private void validateUserStatus(User user) {
        switch (user.getStatus()) {
            case 0: // 禁用
                throw new IllegalArgumentException("账户已被禁用，请联系管理员");
            case 2: // 待审批
                throw new IllegalArgumentException("账户正在审批中，请耐心等待");
            case 3: // 已锁定
                throw new IllegalArgumentException("账户已被锁定，请稍后再试或联系管理员");
            case 1: // 启用
                break; // 状态正常，继续执行
            default:
                throw new IllegalArgumentException("账户状态异常，请联系管理员");
        }
    }

    /**
     * 构造登录响应对象的辅助方法。
     */
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
    
    /**
     * 模拟查询用户角色。
     * @param userId 用户ID
     * @return 角色列表
     */
    private List<String> findUserRoles(Long userId) {
        // TODO: 实现真实的数据库查询逻辑, 关联 t_user_role 和 t_role 表
        return Collections.singletonList("普通帮扶干部"); // 返回模拟数据
    }

    /**
     * 模拟查询用户权限。
     * @param userId 用户ID
     * @return 权限标识符列表
     */
    private List<String> findUserPermissions(Long userId) {
        // TODO: 实现真实的数据库查询逻辑, 关联 t_user_role, t_role_permission, t_permission 表
        return Collections.singletonList("profile:view"); // 返回模拟数据
    }
}