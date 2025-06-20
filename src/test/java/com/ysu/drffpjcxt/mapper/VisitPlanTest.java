package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.VisitPlan;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * VisitPlanMapper的集成测试类。
 * 注意：此类中的测试不会回滚事务，所有数据库操作都是永久性的。
 * 通过 @Transactional 和 @Rollback(false) 明确了这一行为。
 * 测试方法被标记为有序执行，以保证CRUD操作的逻辑连续性。
 */
@SpringBootTest
@Transactional
@Rollback(false) // 明确指出测试不回滚
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitPlanTest {

    @Autowired
    private VisitPlanMapper visitPlanMapper;

    // 静态变量，用于在各个测试方法间共享数据
    private static Long testPlanId;
    private static VisitPlan testVisitPlan;
    private static String plannerIdForTest; // 模拟一个计划制定者的ID

    /**
     * 在所有测试开始前执行一次，初始化共享的测试数据。
     */
    @BeforeAll
    static void setUp() {
        // 模拟一个创建计划的用户ID
        plannerIdForTest = "user-" + UUID.randomUUID().toString().substring(0, 8);

        // 初始化一个包含所有字段的 VisitPlan 对象
        testVisitPlan = new VisitPlan();
        // 注意：不手动设置ID，依赖数据库自动生成
        testVisitPlan.setPlanName("2025年第一季度走访计划");
        testVisitPlan.setPlannerId(8888888L);
        testVisitPlan.setStartDate(new Date()); // 计划开始于当前时间
        testVisitPlan.setEndDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30)); // 计划结束于30天后
        testVisitPlan.setDescription("本次走访重点关注农户的春耕准备情况和信贷需求。");
        testVisitPlan.setStatus("进行中"); // 假设的状态值
        testVisitPlan.setIsDeleted(false);
        testVisitPlan.setCreateTime(new Date());
        testVisitPlan.setUpdateTime(new Date());
    }

    /**
     * 测试1：插入完整的走访计划数据
     */
    @Test
    @Order(1)
    @DisplayName("1. 测试插入走访计划 (不回滚)")
    void testInsert() {
        // 前提: VisitPlanMapper.xml 中的 <insert> 标签必须配置 useGeneratedKeys="true" 和 keyProperty="id"
        int result = visitPlanMapper.insert(testVisitPlan);
        assertEquals(1, result, "走访计划插入失败，未影响任何行。");

        // MyBatis应已将数据库生成的ID回填到 testVisitPlan 对象中
        testPlanId = testVisitPlan.getId();

        assertNotNull(testPlanId, "数据库未能返回生成的ID。请检查 VisitPlanMapper.xml 中 <insert> 标签的配置。");
        assertTrue(testPlanId > 0, "数据库返回的ID不是一个有效的正数。");
        System.out.println("testInsert: 走访计划 '" + testVisitPlan.getPlanName() + "' 已成功插入，生成ID为: " + testPlanId);
    }

    /**
     * 测试2：根据ID查询走访计划
     */
    @Test
    @Order(2)
    @DisplayName("2. 测试根据ID查询走访计划")
    void testQueryById() {
        assertNotNull(testPlanId, "testPlanId 为空，前置的插入测试可能已失败。");

        VisitPlan foundPlan = visitPlanMapper.queryById(testPlanId);

        assertNotNull(foundPlan, "未查询到ID为 " + testPlanId + " 的走访计划。");
        assertEquals(testVisitPlan.getPlanName(), foundPlan.getPlanName(), "查询到的计划名称不匹配。");
        assertEquals(testVisitPlan.getDescription(), foundPlan.getDescription(), "查询到的计划描述不匹配。");
        System.out.println("testQueryById: 成功查询到走访计划 '" + foundPlan.getPlanName() + "'");
    }

    /**
     * 测试3：更新走访计划信息
     */
    @Test
    @Order(3)
    @DisplayName("3. 测试更新走访计划")
    void testUpdate() {
        assertNotNull(testPlanId, "testPlanId 为空，前置的插入测试可能已失败。");
        VisitPlan planToUpdate = visitPlanMapper.queryById(testPlanId);
        assertNotNull(planToUpdate, "更新前未找到走访计划，无法继续测试。");

        String updatedDescription = "更新：除春耕外，还需了解农户对新型农业保险的兴趣。";
        planToUpdate.setDescription(updatedDescription);
        planToUpdate.setStatus("已更新");
        planToUpdate.setUpdateTime(new Date());

        int result = visitPlanMapper.update(planToUpdate);
        assertEquals(1, result, "走访计划更新失败。");

        VisitPlan updatedPlan = visitPlanMapper.queryById(testPlanId);
        assertEquals(updatedDescription, updatedPlan.getDescription(), "计划描述更新后不一致。");
        assertEquals("已更新", updatedPlan.getStatus(), "计划状态更新后不一致。");
        System.out.println("testUpdate: 走访计划描述已成功更新。");
    }

    /**
     * 测试4：根据ID删除走访计划
     */
    @Test
    @Order(4)
    @DisplayName("4. 测试根据ID删除走访计划")
    void testDeleteById() {
        assertNotNull(testPlanId, "testPlanId 为空，前置的插入测试可能已失败。");

        int result = visitPlanMapper.deleteById(testPlanId);
        assertEquals(1, result, "走访计划删除失败。");

        VisitPlan deletedPlan = visitPlanMapper.queryById(testPlanId);
        assertNull(deletedPlan, "走访计划删除后仍能被查询到，删除操作可能未生效。");
        System.out.println("testDeleteById: 走访计划 " + testPlanId + " 已成功从数据库删除。");
    }
}
