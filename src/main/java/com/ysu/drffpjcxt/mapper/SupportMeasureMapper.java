package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.SupportMeasure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 帮扶措施表(SupportMeasure)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Mapper
public interface SupportMeasureMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportMeasure queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param SupportMeasure 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<SupportMeasure> queryAllByLimit(SupportMeasure SupportMeasure, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param SupportMeasure 查询条件
     * @return 总行数
     */
    long count(SupportMeasure SupportMeasure);

    /**
     * 新增数据
     *
     * @param SupportMeasure 实例对象
     * @return 影响行数
     */
    int insert(SupportMeasure SupportMeasure);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SupportMeasure> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SupportMeasure> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SupportMeasure> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SupportMeasure> entities);

    /**
     * 修改数据
     *
     * @param SupportMeasure 实例对象
     * @return 影响行数
     */
    int update(SupportMeasure SupportMeasure);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

    List<SupportMeasure> queryByPlanId(long L);

    /**
     * (新增) 根据计划ID逻辑删除所有关联的措施
     * @param planId 帮扶计划ID
     * @return 受影响的行数
     */
    int softDeleteByPlanId(Long planId);

    void updateStatus(@Param("id") Long id, @Param("status") String status);

}
