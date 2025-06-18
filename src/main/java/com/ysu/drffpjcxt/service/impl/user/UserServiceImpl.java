package com.ysu.drffpjcxt.service.impl.user;

import com.ysu.drffpjcxt.common.JwtUtil;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.dto.user.UserProfileUpdateRequest;
import com.ysu.drffpjcxt.entity.vo.user.UserProfileVO;
import com.ysu.drffpjcxt.exception.auth.AuthException;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserProfileVO getUserProfile(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser")) {
            throw new AuthException("无法获取当前用户信息，请重新登录。");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userMapper.findByPhone(userDetails.getUsername());

        if (user == null) {
            throw new AuthException("用户不存在，数据可能已变更。");
        }

        return convertToUserProfileVO(user);
    }

    @Override
    public UserProfileVO updateUserProfile(Principal principal, UserProfileUpdateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser")) {
            throw new AuthException("无法获取当前用户信息，请重新登录。");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userMapper.findByPhone(userDetails.getUsername());

        if (user == null) {
            throw new AuthException("用户不存在，无法更新。");
        }

        BeanUtils.copyProperties(request, user);
        user.setUpdateTime(new Date());

        userMapper.update(user);
        return convertToUserProfileVO(user);
    }

    /**
     * 根据 Principal 获取用户实体
     */
    private User findUserByPrincipal(Principal principal) {
        if (principal == null || !StringUtils.hasText(principal.getName())) {
            throw new AuthException("无法获取当前用户信息，请重新登录。");
        }
        String phone = principal.getName();
        User user = userMapper.findByPhone(phone);
        if (user == null) {
            throw new AuthException("用户不存在，请重新登录。");
        }
        return user;
    }

    /**
     * 将 User 实体转换为 UserProfileVO 视图对象
     */
    private UserProfileVO convertToUserProfileVO(User user) {
        UserProfileVO vo = new UserProfileVO();
        BeanUtils.copyProperties(user, vo);

        // 身份证号脱敏处理
        if (StringUtils.hasText(user.getIdCard()) && user.getIdCard().length() == 18) {
            vo.setIdCard(user.getIdCard().replaceAll("(\\d{6})\\d{8}(\\w{4})", "$1********$2"));
        }
        return vo;
    }

    // --- 已有的其他方法实现 ---
    @Override
    public User queryById(Object id) {
        return userMapper.queryById(id);
    }

    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        // 分页查询逻辑需要自己实现
        return null;
    }

    @Override
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.update(user);
        return user;
    }

    @Override
    public boolean deleteById(Object id) {
        return userMapper.deleteById(id) > 0;
    }
}