package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.ApprovalRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ApprovalRecordMapper 测试类
 *
 * 【标准测试模式】
 * 使用 @Transactional 注解，测试将在事务中运行，并在结束后自动回滚。
 * 这能确保数据库在每次测试后都保持干净，是推荐的做法。
 */
@SpringBootTest
@Transactional
@Rollback(false)
@DisplayName("审批记录Mapper测试（数据自动回滚）")
class ApprovalRecordMapperTest {

    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;

    /**
     * 创建一个通用的测试对象
     */
    private ApprovalRecord createDemoRecord() {
        ApprovalRecord record = new ApprovalRecord();
        // id 是自增的，不需要我们设置
        record.setBusinessType("HIGH_RISK_APP"); // 业务类型
        record.setBusinessId(101L);             // 关联的业务ID
        record.setStepName("乡级审批");
        record.setApproverId(202L);              // 审批人ID
        record.setResult(1);                    // 审批结果: 1-通过
        record.setComments("情况属实，审批通过。");
        record.setApprovalTime(new Date());
        record.setIsDeleted(false);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        return record;
    }

    @Test
    @DisplayName("测试 - 新增审批记录")
    void testInsert() {
        ApprovalRecord record = createDemoRecord();

        // 执行插入
        approvalRecordMapper.insert(record);

        // id 是自增的，MyBatis会自动回填到实体对象中
        assertNotNull(record.getId(), "插入后，实体对象的ID不应为null");
        System.out.println("成功在事务中插入一条记录，ID为: " + record.getId());

        // 在事务中查出，验证数据是否正确
        ApprovalRecord fromDb = approvalRecordMapper.queryById(record.getId());
        assertNotNull(fromDb, "根据新ID查询，结果不应为null");
        assertEquals("乡级审批", fromDb.getStepName());
        assertEquals(1, fromDb.getResult());
    }

    @Test
    @DisplayName("测试 - 根据ID查询")
    void testQueryById() {
        // 前提：先插入一条数据
        ApprovalRecord record = createDemoRecord();
        approvalRecordMapper.insert(record);
        Long newId = record.getId();

        // 执行查询
        ApprovalRecord fromDb = approvalRecordMapper.queryById(newId);

        // 验证
        assertNotNull(fromDb, "查询结果不应为null");
        assertEquals(newId, fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 更新审批记录")
    void testUpdate() {
        // 前提：先插入一条数据
        ApprovalRecord record = createDemoRecord();
        approvalRecordMapper.insert(record);
        Long newId = record.getId();

        // 准备更新数据
        ApprovalRecord toUpdate = new ApprovalRecord();
        toUpdate.setId(newId); // 必须指定要更新的记录的ID
        toUpdate.setResult(2); // 将结果改为 2-驳回
        toUpdate.setComments("申请材料不全，予以驳回。");

        // 执行更新
        approvalRecordMapper.update(toUpdate);

        // 验证更新结果
        ApprovalRecord fromDb = approvalRecordMapper.queryById(newId);
        assertEquals(2, fromDb.getResult(), "审批结果更新失败");
        assertEquals("申请材料不全，予以驳回。", fromDb.getComments(), "审批意见更新失败");
        System.out.println("成功在事务中更新记录，ID为: " + fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 根据ID删除")
    void testDeleteById() {
        // 前提：先插入一条数据
        ApprovalRecord record = createDemoRecord();
        approvalRecordMapper.insert(record);
        Long newId = record.getId();

        // 确认数据已存在
        assertNotNull(approvalRecordMapper.queryById(newId), "删除前数据应存在");
        System.out.println("准备在事务中删除记录，ID为: " + newId);

        // 执行删除
        approvalRecordMapper.deleteById(newId);

        // 验证是否已删除
        ApprovalRecord fromDb = approvalRecordMapper.queryById(newId);
        assertNull(fromDb, "删除后，根据ID应查询不到记录");
        System.out.println("成功在事务中删除记录，ID为: " + newId);
    }
}