package com.ysu.drffpjcxt.mapper;


import com.ysu.drffpjcxt.entity.AdministrativeDivision;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 行政区划表(AdministrativeDivision)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:12
 */
@Mapper
public interface AdministrativeDivisionMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    AdministrativeDivision queryById(String code);

    /**
     * 查询指定行数据
     *
     * @param administrativeDivision 查询条件
     * @param pageable                分页对象
     * @return 对象列表
     */
    // 正确：将返回类型修改为 List
    List<AdministrativeDivision> queryAllByLimit(AdministrativeDivision administrativeDivision, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param administrativeDivision 查询条件
     * @return 总行数
     */
    long count(AdministrativeDivision administrativeDivision);

    /**
     * 新增数据
     *
     * @param administrativeDivision 实例对象
     * @return 影响行数
     */
    int insert(AdministrativeDivision administrativeDivision);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdministrativeDivision> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdministrativeDivision> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdministrativeDivision> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AdministrativeDivision> entities);

    /**
     * 修改数据
     *
     * @param administrativeDivision 实例对象
     * @return 影响行数
     */
    int update(AdministrativeDivision administrativeDivision);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 影响行数
     */
    int deleteById(String code);

}

