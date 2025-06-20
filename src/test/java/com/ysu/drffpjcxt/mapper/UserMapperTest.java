package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.User;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserMapper的集成测试类.
 * 注意：此类中的测试不会回滚事务，所有数据库操作都是永久性的。
 * 通过 @Transactional 和 @Rollback(false) 明确了这一行为。
 * 测试方法被标记为有序执行，以保证CRUD操作的逻辑连续性。
 */
@SpringBootTest
@Transactional
@Rollback(false) // 明确指出测试不回滚，符合要求
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    // 使用静态变量来在不同的测试方法间共享数据
    private static Long testUserId;
    private static User testUser;

    /**
     * 在所有测试开始前执行一次，用于初始化共享的测试数据。
     */
    @BeforeAll
    static void setUp() {
        // 初始化一个User对象，用于所有测试方法
        testUser = new User();
        testUser.setEmployeeId("EMP-" + UUID.randomUUID().toString().substring(0, 8));
        testUser.setRealName("测试用户1");
        testUser.setPhone("19900101111");
        testUser.setPassword("a_very_secure_password_hash");
        testUser.setIdCard("430124199001011234");
        testUser.setEmail("test1@example.com");
        testUser.setAvatarUrl("https://example1.com/avatar.png");
        testUser.setProvinceCode("430");
        testUser.setCityCode("4301");
        testUser.setCountyCode("4301");
        testUser.setTownshipCode("4301");
        testUser.setDepartmentCode("4301");
        testUser.setDepartmentName("测试部门");
        testUser.setStatus(21);
        testUser.setIsDeleted(false);
        testUser.setCreateTime(new Date());
        testUser.setUpdateTime(new Date());
    }

    /**
     * 测试1：插入用户数据，并获取数据库生成的ID
     */
    @Test
    @Order(1)
    @DisplayName("1. 测试插入用户 (不回滚)")
    void testInsert() {
        // 前提: UserMapper.xml 中的 <insert> 标签必须配置 useGeneratedKeys="true" 和 keyProperty="id"
        // 并且 User 实体类中的 id 字段类型为 Long
        int result = userMapper.insert(testUser);
        assertEquals(1, result, "用户插入失败，未影响任何行。");

        // MyBatis应已将数据库生成的ID回填到testUser对象中
        testUserId = testUser.getId();

        assertNotNull(testUserId, "数据库未能返回生成的ID。请检查 UserMapper.xml 中 <insert> 标签的 `useGeneratedKeys` 和 `keyProperty` 属性是否正确设置。");
        assertTrue(testUserId > 0, "数据库返回的ID不是一个有效的正数。");
        System.out.println("testInsert: 用户 " + testUser.getRealName() + " 已成功插入数据库，生成ID为: " + testUserId);
    }

    /**
     * 测试2：根据数据库生成的ID查询用户
     */
    @Test
    @Order(2)
    @DisplayName("2. 测试根据ID查询用户")
    void testQueryById() {
        assertNotNull(testUserId, "testUserId 为空，前置的插入测试可能失败了，请先检查 testInsert()。");

        // 前提: UserMapper 接口中的 queryById 方法参数类型应为 Long
        User foundUser = userMapper.queryById(testUserId);

        assertNotNull(foundUser, "未查询到ID为 " + testUserId + " 的用户。");
        assertEquals(testUser.getRealName(), foundUser.getRealName(), "查询到的用户名不匹配。");
        assertEquals(testUser.getPhone(), foundUser.getPhone(), "查询到的手机号不匹配。");
        System.out.println("testQueryById: 成功查询到用户 " + foundUser.getRealName());
    }

    /**
     * 测试3：更新用户信息
     */
    @Test
    @Order(3)
    @DisplayName("3. 测试更新用户信息")
    void testUpdate() {
        assertNotNull(testUserId, "testUserId 为空，前置的插入测试可能失败了，请先检查 testInsert()。");
        User userToUpdate = userMapper.queryById(testUserId);
        assertNotNull(userToUpdate, "更新前未找到用户，无法继续测试。");

        String updatedName = "测试用户(已更新)";
        userToUpdate.setRealName(updatedName);
        userToUpdate.setUpdateTime(new Date());

        int result = userMapper.update(userToUpdate);
        assertEquals(1, result, "用户更新失败。");

        User updatedUser = userMapper.queryById(testUserId);
        assertEquals(updatedName, updatedUser.getRealName(), "用户名更新后不一致。");
        System.out.println("testUpdate: 用户名已成功更新为 " + updatedUser.getRealName());
    }

    /**
     * 测试4：根据ID删除用户
     */
    @Test
    @Order(4)
    @DisplayName("4. 测试根据ID删除用户")
    void testDeleteById() {
        assertNotNull(testUserId, "testUserId 为空，前置的插入测试可能失败了，请先检查 testInsert()。");

        // 前提: UserMapper 接口中的 deleteById 方法参数类型应为 Long
        int result = userMapper.deleteById(testUserId);
        assertEquals(1, result, "用户删除失败。");

        User deletedUser = userMapper.queryById(testUserId);
        assertNull(deletedUser, "用户删除后仍能被查询到，删除操作可能未生效。");
        System.out.println("testDeleteById: 用户 " + testUserId + " 已成功从数据库删除。");
    }
}
