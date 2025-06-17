package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.ApplicationAttachment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ApplicationAttachmentMapper 测试类
 *
 * 【注意】
 * 此测试类配置为“不回滚”。
 * 类级别的 @Transactional 和 @Rollback(false) 注解会确保
 * 每个测试方法执行后，其数据库操作都会被真实提交。
 */
@SpringBootTest
@Transactional
@Rollback(false)
@DisplayName("申请佐证材料Mapper测试（数据不回滚）")
class ApplicationAttachmentMapperTest {

    @Autowired
    private ApplicationAttachmentMapper applicationAttachmentMapper;

    /**
     * 创建一个通用的测试对象
     */
    private ApplicationAttachment createDemoAttachment() {
        ApplicationAttachment attachment = new ApplicationAttachment();
        // id 是自增的，不需要我们设置
        attachment.setApplicationId(1001L); // 假设关联的申请ID是1001
        attachment.setOriginalFileName("测试佐证材料.pdf");
        attachment.setFilePath("/attachments/test.pdf");
        attachment.setFileType("application/pdf");
        attachment.setFileSize(2048L); // 2KB
        attachment.setIsDeleted(false);
        attachment.setUploadedAt(new Date());
        attachment.setCreateTime(new Date());
        attachment.setUpdateTime(new Date());
        return attachment;
    }

    @Test
    @DisplayName("测试 - 新增附件")
    void testInsert() {
        ApplicationAttachment attachment = createDemoAttachment();

        // 执行插入
        applicationAttachmentMapper.insert(attachment);

        // 因为 id 是自增的，并且在 XML 中配置了 keyProperty="id",
        // 所以执行插入后，attachment 对象的 id 字段会被自动回填。
        assertNotNull(attachment.getId(), "插入后，实体对象的ID不应为null");
        System.out.println("成功插入一条记录，ID为: " + attachment.getId());

        // 从数据库查出，验证数据是否正确
        ApplicationAttachment fromDb = applicationAttachmentMapper.queryById(attachment.getId());
        assertNotNull(fromDb, "根据新ID查询，结果不应为null");
        assertEquals("测试佐证材料.pdf", fromDb.getOriginalFileName());
    }

    @Test
    @DisplayName("测试 - 根据ID查询")
    void testQueryById() {
        // 前提：先插入一条数据
        ApplicationAttachment attachment = createDemoAttachment();
        applicationAttachmentMapper.insert(attachment);
        Long newId = attachment.getId();

        // 执行查询
        ApplicationAttachment fromDb = applicationAttachmentMapper.queryById(newId);

        // 验证
        assertNotNull(fromDb, "查询结果不应为null");
        assertEquals(newId, fromDb.getId());
        System.out.println("成功查询到记录，ID为: " + fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 更新附件信息")
    void testUpdate() {
        // 前提：先插入一条数据
        ApplicationAttachment attachment = createDemoAttachment();
        applicationAttachmentMapper.insert(attachment);
        Long newId = attachment.getId();

        // 准备更新数据
        ApplicationAttachment toUpdate = new ApplicationAttachment();
        toUpdate.setId(newId); // 必须指定要更新的记录的ID
        toUpdate.setOriginalFileName("已更新的佐证材料.zip");

        // 执行更新
        applicationAttachmentMapper.update(toUpdate);

        // 验证更新结果
        ApplicationAttachment fromDb = applicationAttachmentMapper.queryById(newId);
        assertEquals("已更新的佐证材料.zip", fromDb.getOriginalFileName(), "文件名更新失败");
        assertNotNull(fromDb.getFileType(), "未更新的字段不应变为null");
        System.out.println("成功更新记录，ID为: " + fromDb.getId());
    }

    @Test
    @DisplayName("测试 - 根据ID删除")
    void testDeleteById() {
        // 前提：先插入一条数据
        ApplicationAttachment attachment = createDemoAttachment();
        applicationAttachmentMapper.insert(attachment);
        Long newId = attachment.getId();
        System.out.println("准备删除记录，ID为: " + newId);

        // 执行删除
        applicationAttachmentMapper.deleteById(newId);

        // 验证是否已删除
        ApplicationAttachment fromDb = applicationAttachmentMapper.queryById(newId);
        assertNull(fromDb, "删除后，根据ID应查询不到记录");
        System.out.println("成功删除记录，ID为: " + newId);
    }
}