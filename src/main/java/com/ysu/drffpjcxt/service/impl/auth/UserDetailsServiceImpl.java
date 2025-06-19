package com.ysu.drffpjcxt.service.impl.auth;

import com.ysu.drffpjcxt.entity.Role;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.UserRole;
import com.ysu.drffpjcxt.mapper.RoleMapper;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.mapper.UserRoleMapper;
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

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        // 通过手机号从数据库查找用户
        User user = userMapper.findByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + phone);
        }

        // 从数据库加载用户的真实角色和权限
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 查询该用户的所有角色
        List<UserRole> userRoles = userRoleMapper.findByUserId(user.getId());

        // 如果用户没有分配角色，给予默认的ROLE_USER角色
        if (userRoles == null || userRoles.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            // 根据角色ID查询角色信息并添加到权限列表
            for (UserRole userRole : userRoles) {
                Role role = roleMapper.queryById(userRole.getRoleId());
                if (role != null) {
                    // Spring Security要求角色名称必须以ROLE_开头
                    String roleName = role.getRoleCode();
                    authorities.add(new SimpleGrantedAuthority(roleName));
                }
            }
        }

        // 返回Spring Security能够理解的UserDetails对象
        return new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPassword(),
                authorities // 用户的权限列表
        );
    }
}