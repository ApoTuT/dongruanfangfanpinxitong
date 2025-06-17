package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * RoleMapper 测试类
 *
 * 【标准测试模式】
 * 使用 @Transactional 注解，测试将在事务中运行，并在结束后自动回滚。
 */
@SpringBootTest
@Transactional
@DisplayName("角色Mapper测试（数据自动回滚）")
class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 创建一个通用的测试对象
     */
    private Role createDemoRole() {
        Role role = new Role();
        // id 是自增的，不需要我们设置
        role.setRoleName("测试角色");
        role.setRoleCode("TEST_ROLE");
        role.setDescription("这是一个用于单元测试的角色");
        role.setIsDeleted(false);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        return role;
    }

    @Test
    @DisplayName("测试 - 新增角色")
    void testInsert() {
        Role role = createDemoRole();

        // 执行插入
        roleMapper.insert(role);

        // id 是自增的，MyBatis会自动回填到实体对象中
        assertNotNull(role.getId(), "插入后，实体对象的ID不应为null");
        System.out.println("成功在事务中插入一条记录，ID为: " + role.getId());

        // 在事务中查出，验证数据是否正确
        Role fromDb = roleMapper.queryById(role.getId());
        assertNotNull(fromDb, "根据新ID查询，结果不应为null");
        assertEquals("测试角色", fromDb.getRoleName());
    }

    @Test
    @DisplayName("测试 - 根据ID查询")
    void testQueryById() {
        // 前提：先插入一条数据
        Role role = createDemoRole();
        roleMapper.insert(role);
        Long newId = role.getId();

        // 执行查询
        Role fromDb = roleMapper.queryById(newId);

        // 验证
        assertNotNull(fromDb, "查询结果不应为null");
        assertEquals(newId, fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 更新角色")
    void testUpdate() {
        // 前提：先插入一条数据
        Role role = createDemoRole();
        roleMapper.insert(role);
        Long newId = role.getId();

        // 准备更新数据
        Role toUpdate = new Role();
        toUpdate.setId(newId); // 必须指定要更新的记录的ID
        toUpdate.setRoleName("已更新的测试角色");
        toUpdate.setDescription("描述信息也已更新");

        // 执行更新
        roleMapper.update(toUpdate);

        // 验证更新结果
        Role fromDb = roleMapper.queryById(newId);
        assertEquals("已更新的测试角色", fromDb.getRoleName(), "角色名称更新失败");
        assertEquals("已更新的测试角色", fromDb.getRoleName(), "描述信息更新失败");
    }

    @Test
    @DisplayName("测试 - 根据ID删除")
    void testDeleteById() {
        // 前提：先插入一条数据
        Role role = createDemoRole();
        roleMapper.insert(role);
        Long newId = role.getId();

        // 确认数据已存在
        assertNotNull(roleMapper.queryById(newId), "删除前数据应存在");

        // 执行删除
        roleMapper.deleteById(newId);

        // 验证是否已删除
        assertNull(roleMapper.queryById(newId), "删除后，根据ID应查询不到记录");
    }
}