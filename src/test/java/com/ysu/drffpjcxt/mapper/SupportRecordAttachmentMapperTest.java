package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.SupportRecordAttachment;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SupportRecordAttachmentMapper的集成测试类。
 * 注意：此类中的测试不会回滚事务，所有数据库操作都是永久性的。
 * 通过 @Transactional 和 @Rollback(false) 明确了这一行为。
 * 测试方法被标记为有序执行，以保证CRUD操作的逻辑连续性。
 */
@SpringBootTest
@Transactional
@Rollback(false) // 明确指出测试不回滚
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupportRecordAttachmentMapperTest {

    @Autowired
    private SupportRecordAttachmentMapper supportRecordAttachmentMapper;

    // 静态变量，用于在各个测试方法间共享数据
    private static Long testAttachmentId; // ID类型已修改为Long
    private static SupportRecordAttachment testAttachment;

    /**
     * 在所有测试开始前执行一次，初始化共享的测试数据。
     */
    @BeforeAll
    static void setUp() {
        // **FIX: Provide a valid Long for recordId to satisfy the database constraint.**
        // In a real scenario, this ID would come from an actual SupportRecord.
        long testRecordId = 1L;

        // 初始化一个包含所有字段的 SupportRecordAttachment 对象
        testAttachment = new SupportRecordAttachment();
        // **注意：不再手动设置ID，依赖数据库自增**
        testAttachment.setRecordId(555L);
        testAttachment.setAttachmentType("身份证照片");
        testAttachment.setOriginalFileName("zhangsan_id_card.jpg");
        testAttachment.setFilePath("/uploads/attachments/zhangsan_id_card.jpg");
        testAttachment.setIsDeleted(false);
        testAttachment.setCreateTime(new Date());
        testAttachment.setUpdateTime(new Date());
    }

    /**
     * 测试1：插入完整的帮扶记录附件数据，并获取数据库生成的ID
     */
    @Test
    @Order(1)
    @DisplayName("1. 测试插入帮扶记录附件 (不回滚)")
    void testInsert() {
        // 前提: SupportRecordAttachmentMapper.xml 中 <insert> 标签必须配置 useGeneratedKeys="true" 和 keyProperty="id"
        // 并且实体类的id字段为Long类型
        int result = supportRecordAttachmentMapper.insert(testAttachment);
        assertEquals(1, result, "附件记录插入失败，未影响任何行。");

        // 从对象中获取由数据库回填的ID
        testAttachmentId = testAttachment.getId();

        assertNotNull(testAttachmentId, "数据库未能返回生成的ID。请检查XML配置。");
        assertTrue(testAttachmentId > 0, "数据库返回的ID不是一个有效的正数。");
        System.out.println("testInsert: 附件 '" + testAttachment.getOriginalFileName() + "' 已成功插入，生成ID为: " + testAttachmentId);
    }

    /**
     * 测试2：根据ID查询帮扶记录附件
     */
    @Test
    @Order(2)
    @DisplayName("2. 测试根据ID查询附件")
    void testQueryById() {
        assertNotNull(testAttachmentId, "testAttachmentId 为空，前置的插入测试可能已失败。");

        SupportRecordAttachment foundAttachment = supportRecordAttachmentMapper.queryById(testAttachmentId);

        assertNotNull(foundAttachment, "未查询到ID为 " + testAttachmentId + " 的附件记录。");
        assertEquals(testAttachment.getOriginalFileName(), foundAttachment.getOriginalFileName(), "查询到的原始文件名不匹配。");
        assertEquals(testAttachment.getFilePath(), foundAttachment.getFilePath(), "查询到的文件路径不匹配。");
        System.out.println("testQueryById: 成功查询到附件 '" + foundAttachment.getOriginalFileName() + "'");
    }

    /**
     * 测试3：更新帮扶记录附件信息
     */
    @Test
    @Order(3)
    @DisplayName("3. 测试更新附件信息")
    void testUpdate() {
        assertNotNull(testAttachmentId, "testAttachmentId 为空，前置的插入测试可能已失败。");
        SupportRecordAttachment attachmentToUpdate = supportRecordAttachmentMapper.queryById(testAttachmentId);
        assertNotNull(attachmentToUpdate, "更新前未找到附件记录，无法继续测试。");

        String updatedFileName = "zhangsan_id_card_front.jpg";
        attachmentToUpdate.setOriginalFileName(updatedFileName);
        attachmentToUpdate.setUpdateTime(new Date());

        int result = supportRecordAttachmentMapper.update(attachmentToUpdate);
        assertEquals(1, result, "附件记录更新失败。");

        SupportRecordAttachment updatedAttachment = supportRecordAttachmentMapper.queryById(testAttachmentId);
        assertEquals(updatedFileName, updatedAttachment.getOriginalFileName(), "原始文件名更新后不一致。");
        System.out.println("testUpdate: 附件原始文件名已成功更新。");
    }

    /**
     * 测试4：根据ID删除帮扶记录附件
     */
    @Test
    @Order(4)
    @DisplayName("4. 测试根据ID删除附件")
    void testDeleteById() {
        assertNotNull(testAttachmentId, "testAttachmentId 为空，前置的插入测试可能已失败。");

        int result = supportRecordAttachmentMapper.deleteById(testAttachmentId);
        assertEquals(1, result, "附件记录删除失败。");

        SupportRecordAttachment deletedAttachment = supportRecordAttachmentMapper.queryById(testAttachmentId);
        assertNull(deletedAttachment, "附件记录删除后仍能被查询到，删除操作可能未生效。");
        System.out.println("testDeleteById: 附件记录 " + testAttachmentId + " 已成功从数据库删除。");
    }
}
