package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.SupportPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 帮扶计划表(SupportPlan)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Mapper
public interface SupportPlanMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportPlan queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param tSupportPlan 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<SupportPlan> queryAllByLimit(SupportPlan tSupportPlan, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param SupportPlan 查询条件
     * @return 总行数
     */
    long count(SupportPlan SupportPlan);

    /**
     * 新增数据
     *
     * @param SupportPlan 实例对象
     * @return 影响行数
     */
    int insert(SupportPlan SupportPlan);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SupportPlan> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SupportPlan> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SupportPlan> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SupportPlan> entities);

    /**
     * 修改数据
     *
     * @param SupportPlan 实例对象
     * @return 影响行数
     */
    int update(SupportPlan SupportPlan);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

