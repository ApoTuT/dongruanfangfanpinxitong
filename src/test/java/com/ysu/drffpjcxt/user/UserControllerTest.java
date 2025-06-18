package com.ysu.drffpjcxt.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.dto.user.UserProfileUpdateRequest;
import com.ysu.drffpjcxt.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserController 的集成测试类。
 * 用于测试用户自我管理相关的功能接口。
 */
@SpringBootTest
@AutoConfigureMockMvc // 启用并自动配置 MockMvc，用于模拟HTTP请求
@Transactional
@DisplayName("用户个人信息管理接口测试")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // 模拟HTTP请求的核心工具

    @Autowired
    private UserMapper userMapper; // 用于直接操作数据库，准备测试数据

    @Autowired
    private PasswordEncoder passwordEncoder; // 用于加密密码

    @Autowired
    private ObjectMapper objectMapper; // 用于将Java对象序列化为JSON字符串

    // 定义一个静态的手机号，用于测试
    private static final String TEST_USER_PHONE = "13912345678";
    private User currentUser;

    /**
     * 在每个测试方法执行前运行，用于准备一个干净的测试环境。
     */
    @BeforeEach
    void setUp() {
        // 创建一个用于测试的用户并存入数据库
        currentUser = new User();
        currentUser.setEmployeeId("EMP-" + UUID.randomUUID().toString().substring(0, 8));
        currentUser.setRealName("测试用户");
        currentUser.setPhone(TEST_USER_PHONE);
        currentUser.setPassword(passwordEncoder.encode("password123")); // 加密密码
        currentUser.setIdCard("130101199001011234");
        currentUser.setEmail("test@example.com");
        currentUser.setStatus(1); // 1-启用状态
        currentUser.setIsDeleted(false);
        currentUser.setCreateTime(new Date());
        currentUser.setUpdateTime(new Date());
        userMapper.insert(currentUser);
    }

    @Test
    @DisplayName("1. 成功获取个人资料")
    // 使用 @WithMockUser 模拟一个已登录的用户。
    // username 属性会成为 Principal.getName() 的返回值。在我们的实现中，它就是手机号。
    @WithMockUser(username = TEST_USER_PHONE)
    void testGetUserProfile_Success() throws Exception {
        mockMvc.perform(get("/api/user/profile"))
                .andExpect(status().isOk()) // 验证HTTP状态码是否为 200 OK
                .andExpect(jsonPath("$.id").value(currentUser.getId())) // 验证返回的JSON中id字段是否正确
                .andExpect(jsonPath("$.realName").value("测试用户")) // 验证姓名
                .andExpect(jsonPath("$.phone").value(TEST_USER_PHONE)) // 验证手机号
                .andExpect(jsonPath("$.idCard").value("130101********1234")); // 验证身份证号是否已脱敏
    }

    @Test
    @DisplayName("2. 成功更新个人资料")
    @WithMockUser(username = TEST_USER_PHONE)
    void testUpdateUserProfile_Success() throws Exception {
        // 准备要更新的数据
        UserProfileUpdateRequest updateRequest = new UserProfileUpdateRequest();
        updateRequest.setRealName("测试用户-已更新");
        updateRequest.setEmail("updated@example.com");

        // 执行PUT请求，并验证返回结果
        mockMvc.perform(put("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest))) // 将请求对象转为JSON字符串
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.realName").value("测试用户-已更新"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));

        // 直接从数据库中再次查询，确认数据是否真的被持久化更新了
        User updatedUserInDb = userMapper.findByPhone(TEST_USER_PHONE);
        assertEquals("测试用户-已更新", updatedUserInDb.getRealName());
        assertEquals("updated@example.com", updatedUserInDb.getEmail());
        assertNotEquals(currentUser.getUpdateTime(), updatedUserInDb.getUpdateTime()); // 确认更新时间已改变
    }

    @Test
    @DisplayName("3. 未登录用户访问个人资料接口应被拒绝")
    void testGetUserProfile_Unauthorized() throws Exception {
        // 不使用 @WithMockUser，模拟一个未登录的请求
        mockMvc.perform(get("/api/user/profile"))
                .andExpect(status().isForbidden()); // Spring Security 默认返回 403 Forbidden
    }
}