package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.ReportTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 报告模板表(ReportTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:25
 */
@Mapper
public interface ReportTemplateMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReportTemplate queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param ReportTemplate 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<ReportTemplate> queryAllByLimit(ReportTemplate ReportTemplate, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param ReportTemplate 查询条件
     * @return 总行数
     */
    long count(ReportTemplate ReportTemplate);

    /**
     * 新增数据
     *
     * @param ReportTemplate 实例对象
     * @return 影响行数
     */
    int insert(ReportTemplate ReportTemplate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReportTemplate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReportTemplate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReportTemplate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReportTemplate> entities);

    /**
     * 修改数据
     *
     * @param ReportTemplate 实例对象
     * @return 影响行数
     */
    int update(ReportTemplate ReportTemplate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

