package com.ysu.drffpjcxt.service.impl.auth;

import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security 用于在认证流程中加载用户核心信息的服务。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        // 通过手机号从数据库查找用户
        User user = userMapper.findByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + phone);
        }

        // TODO: 在未来，从数据库加载用户的真实角色和权限
        // 目前，我们为所有用户授予一个默认的 "ROLE_USER" 角色
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // 返回Spring Security能够理解的UserDetails对象
        return new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPassword(),
                authorities // 用户的权限列表
        );
    }
}