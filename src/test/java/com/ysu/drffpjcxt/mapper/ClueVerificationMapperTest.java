package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.ClueVerification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ClueVerificationMapper 测试类
 *
 * 【标准测试模式】
 * 使用 @Transactional 注解，测试将在事务中运行，并在结束后自动回滚。
 */
@SpringBootTest
@Transactional
@DisplayName("线索核实记录Mapper测试（数据自动回滚）")
class ClueVerificationMapperTest {

    @Autowired
    private ClueVerificationMapper clueVerificationMapper;

    /**
     * 创建一个通用的测试对象
     */
    private ClueVerification createDemoVerification() {
        ClueVerification verification = new ClueVerification();
        // id 是自增的，不需要我们设置
        verification.setClueId(501L); // 假设关联的线索ID是501
        verification.setVerifierId(202L); // 假设核实人ID是202
        verification.setVerificationDate(new Date());
        verification.setProcessDescription("通过电话和实地走访进行了核实。");
        verification.setConclusion("情况基本属实");
        verification.setDetailedSituation("该户因病导致收入骤减，生活困难。");
        verification.setSuggestion("建议纳入临时救助范围。");
        verification.setIsDeleted(false);
        verification.setCreateTime(new Date());
        verification.setUpdateTime(new Date());
        return verification;
    }

    @Test
    @DisplayName("测试 - 新增线索核实记录")
    void testInsert() {
        ClueVerification verification = createDemoVerification();

        // 执行插入
        clueVerificationMapper.insert(verification);

        // id 是自增的，MyBatis会自动回填到实体对象中
        assertNotNull(verification.getId(), "插入后，实体对象的ID不应为null");
        System.out.println("成功在事务中插入一条记录，ID为: " + verification.getId());

        // 在事务中查出，验证数据是否正确
        ClueVerification fromDb = clueVerificationMapper.queryById(verification.getId());
        assertNotNull(fromDb, "根据新ID查询，结果不应为null");
        assertEquals("情况基本属实", fromDb.getConclusion());
    }

    @Test
    @DisplayName("测试 - 根据ID查询")
    void testQueryById() {
        // 前提：先插入一条数据
        ClueVerification verification = createDemoVerification();
        clueVerificationMapper.insert(verification);
        Long newId = verification.getId();

        // 执行查询
        ClueVerification fromDb = clueVerificationMapper.queryById(newId);

        // 验证
        assertNotNull(fromDb, "查询结果不应为null");
        assertEquals(newId, fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 更新线索核实记录")
    void testUpdate() {
        // 前提：先插入一条数据
        ClueVerification verification = createDemoVerification();
        clueVerificationMapper.insert(verification);
        Long newId = verification.getId();

        // 准备更新数据
        ClueVerification toUpdate = new ClueVerification();
        toUpdate.setId(newId); // 必须指定要更新的记录的ID
        toUpdate.setConclusion("经复核，情况完全属实");
        toUpdate.setSuggestion("建议纳入高风险监测对象。");

        // 执行更新
        clueVerificationMapper.update(toUpdate);

        // 验证更新结果
        ClueVerification fromDb = clueVerificationMapper.queryById(newId);
        assertEquals("经复核，情况完全属实", fromDb.getConclusion(), "核实结论更新失败");
        assertEquals("建议纳入高风险监测对象。", fromDb.getSuggestion(), "处置建议更新失败");
    }

    @Test
    @DisplayName("测试 - 根据ID删除")
    void testDeleteById() {
        // 前提：先插入一条数据
        ClueVerification verification = createDemoVerification();
        clueVerificationMapper.insert(verification);
        Long newId = verification.getId();

        // 确认数据已存在
        assertNotNull(clueVerificationMapper.queryById(newId), "删除前数据应存在");

        // 执行删除
        clueVerificationMapper.deleteById(newId);

        // 验证是否已删除
        assertNull(clueVerificationMapper.queryById(newId), "删除后，根据ID应查询不到记录");
    }
}