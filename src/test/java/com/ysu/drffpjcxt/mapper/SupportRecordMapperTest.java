package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.SupportRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SupportRecordMapper 测试类
 *
 * 【标准测试模式】
 * 使用 @Transactional 注解，测试将在事务中运行，并在结束后自动回滚。
 */
@SpringBootTest
@Transactional
@DisplayName("帮扶记录Mapper测试（数据自动回滚）")
class SupportRecordMapperTest {

    @Autowired
    private SupportRecordMapper supportRecordMapper;

    /**
     * 创建一个通用的测试对象
     */
    private SupportRecord createDemoRecord() {
        SupportRecord record = new SupportRecord();
        // id 是自增的，不需要我们设置
        record.setMeasureId(101L); // 关联措施ID
        record.setPlanId(202L);    // 关联计划ID
        record.setFarmerId(303L);   // 关联农户ID
        record.setActivityDate(new Date());
        record.setActivityContent("首次上门走访，了解家庭情况。");
        record.setResourceInputDescription("提供政策宣传册5本。");
        // 注意：使用字符串构造函数创建 BigDecimal，避免精度问题
        record.setFinancialInputAmount(new BigDecimal("500.00"));
        record.setProgressAndEffect("已建立初步联系。");
        record.setProblemsEncountered("户主对部分政策不理解。");
        record.setCreatedBy(404L); // 记录人ID
        record.setIsDeleted(false);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return record;
    }

    @Test
    @DisplayName("测试 - 新增帮扶记录")
    void testInsert() {
        SupportRecord record = createDemoRecord();

        // 执行插入
        supportRecordMapper.insert(record);

        // id 是自增的，MyBatis会自动回填到实体对象中
        assertNotNull(record.getId(), "插入后，实体对象的ID不应为null");
        System.out.println("成功在事务中插入一条记录，ID为: " + record.getId());

        // 在事务中查出，验证数据是否正确
        SupportRecord fromDb = supportRecordMapper.queryById(record.getId());
        assertNotNull(fromDb, "根据新ID查询，结果不应为null");
        assertEquals("首次上门走访，了解家庭情况。", fromDb.getActivityContent());
        // 比较 BigDecimal 的值
        assertEquals(0, new BigDecimal("500.00").compareTo(fromDb.getFinancialInputAmount()));
    }

    @Test
    @DisplayName("测试 - 根据ID查询")
    void testQueryById() {
        // 前提：先插入一条数据
        SupportRecord record = createDemoRecord();
        supportRecordMapper.insert(record);
        Long newId = record.getId();

        // 执行查询
        SupportRecord fromDb = supportRecordMapper.queryById(newId);

        // 验证
        assertNotNull(fromDb, "查询结果不应为null");
        assertEquals(newId, fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 更新帮扶记录")
    void testUpdate() {
        // 前提：先插入一条数据
        SupportRecord record = createDemoRecord();
        supportRecordMapper.insert(record);
        Long newId = record.getId();

        // 准备更新数据
        SupportRecord toUpdate = new SupportRecord();
        toUpdate.setId(newId); // 必须指定要更新的记录的ID
        toUpdate.setActivityContent("第二次电话回访，问题已解决。");
        toUpdate.setFinancialInputAmount(new BigDecimal("550.50"));

        // 执行更新
        supportRecordMapper.update(toUpdate);

        // 验证更新结果
        SupportRecord fromDb = supportRecordMapper.queryById(newId);
        assertEquals("第二次电话回访，问题已解决。", fromDb.getActivityContent(), "活动内容更新失败");
        assertEquals(0, new BigDecimal("550.50").compareTo(fromDb.getFinancialInputAmount()), "投入资金更新失败");
    }

    @Test
    @DisplayName("测试 - 根据ID删除")
    void testDeleteById() {
        // 前提：先插入一条数据
        SupportRecord record = createDemoRecord();
        supportRecordMapper.insert(record);
        Long newId = record.getId();

        // 确认数据已存在
        assertNotNull(supportRecordMapper.queryById(newId), "删除前数据应存在");

        // 执行删除
        supportRecordMapper.deleteById(newId);

        // 验证是否已删除
        assertNull(supportRecordMapper.queryById(newId), "删除后，根据ID应查询不到记录");
    }
}