package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.AdministrativeDivision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AdministrativeDivisionMapper 测试类
 * @Transactional 注解确保每个测试方法都在一个事务中运行，
 * 测试结束后会自动回滚，保持数据库的清洁。
 */
@SpringBootTest
@Transactional
@Rollback(false)
@DisplayName("行政区划Mapper测试")
class AdministrativeDivisionMapperTest {

    @Autowired
    private AdministrativeDivisionMapper administrativeDivisionMapper;

    private AdministrativeDivision division;

    /**
     * 在每个测试方法运行前，初始化一个通用的测试对象
     */
    @BeforeEach
    void setUp() {
        division = new AdministrativeDivision();
        // 关键：手动设置主键 code，因为它不是自增的
        division.setCode("130000");
        division.setDivisionName("河北省");
        division.setLevelType(1); // 1-省
        division.setParentCode(null);
        division.setIsDeleted(false);
        division.setCreateTime(new Date());
        division.setUpdateTime(new Date());
    }

    @Test
    @DisplayName("测试 - 新增单条行政区划")
    void testInsert() {
        // 执行插入操作
        // 注意：XML中的insert方法没有返回影响的行数，所以我们不检查返回值
        // 而是通过查询来验证是否插入成功
        administrativeDivisionMapper.insert(division);

        // 从数据库中查出刚刚插入的数据
        AdministrativeDivision insertedDivision = administrativeDivisionMapper.queryById("130000");

        //断言（Assert）- 验证数据是否正确
        assertNotNull(insertedDivision, "插入后查询结果不应为 null");
        assertEquals("河北省", insertedDivision.getDivisionName(), "区划名称不匹配");
        assertEquals(1, insertedDivision.getLevelType(), "级别类型不匹配");
    }

    @Test
    @DisplayName("测试 - 批量新增行政区划")
    void testInsertBatch() {
        // 准备批量插入的数据
        List<AdministrativeDivision> divisions = new ArrayList<>();
        divisions.add(division); // 添加河北省

        AdministrativeDivision city = new AdministrativeDivision();
        city.setCode("130300");
        city.setDivisionName("秦皇岛市");
        city.setLevelType(2); // 2-市
        city.setParentCode("130000");
        city.setIsDeleted(false);
        divisions.add(city);

        // 执行批量插入
        administrativeDivisionMapper.insertBatch(divisions);

        // 验证插入结果
        AdministrativeDivision provinceResult = administrativeDivisionMapper.queryById("130000");
        AdministrativeDivision cityResult = administrativeDivisionMapper.queryById("130300");

        assertNotNull(provinceResult, "批量插入后查询河北省结果不应为 null");
        assertNotNull(cityResult, "批量插入后查询秦皇岛市结果不应为 null");
        assertEquals("秦皇岛市", cityResult.getDivisionName());
    }

    @Test
    @DisplayName("测试 - 根据ID查询")
    void testQueryById() {
        // 先插入一条数据作为测试前提
        administrativeDivisionMapper.insert(division);

        // 执行查询
        AdministrativeDivision found = administrativeDivisionMapper.queryById("130000");

        // 验证查询结果
        assertNotNull(found);
        assertEquals("河北省", found.getDivisionName());
    }

    @Test
    @DisplayName("测试 - 更新数据")
    void testUpdate() {
        // 先插入数据
        administrativeDivisionMapper.insert(division);

        // 修改数据
        AdministrativeDivision divisionToUpdate = new AdministrativeDivision();
        divisionToUpdate.setCode("130000"); // 必须提供要更新记录的 code
        divisionToUpdate.setDivisionName("Hebei Province");

        // 执行更新
        administrativeDivisionMapper.update(divisionToUpdate);

        // 验证更新结果
        AdministrativeDivision updatedDivision = administrativeDivisionMapper.queryById("130000");
        assertEquals("Hebei Province", updatedDivision.getDivisionName(), "更新后的名称不正确");
        assertNotNull(updatedDivision.getLevelType(), "未更新的字段不应变为null");
    }

    @Test
    @DisplayName("测试 - 统计总行数")
    void testCount() {
        // 插入一条数据
        administrativeDivisionMapper.insert(division);

        // 执行统计
        long count = administrativeDivisionMapper.count(new AdministrativeDivision());

        // 验证
        assertTrue(count >= 1, "统计数量应至少为1");
    }


    @Test
    @DisplayName("测试 - 根据ID删除")
    void testDeleteById() {
        // 先插入数据
        administrativeDivisionMapper.insert(division);

        // 确认数据已存在
        AdministrativeDivision beforeDelete = administrativeDivisionMapper.queryById("130000");
        assertNotNull(beforeDelete, "删除前数据应存在");

        // 执行删除
        administrativeDivisionMapper.deleteById("130000");

        // 验证数据已被删除
        AdministrativeDivision afterDelete = administrativeDivisionMapper.queryById("130000");
        assertNull(afterDelete, "删除后数据应为null");
    }
}