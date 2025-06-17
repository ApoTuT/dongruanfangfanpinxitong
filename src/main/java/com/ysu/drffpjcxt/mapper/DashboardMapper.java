package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.Dashboard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 仪表盘表(Dashboard)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:18
 */
@Mapper
public interface DashboardMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dashboard queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param dashboard 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<Dashboard> queryAllByLimit(Dashboard dashboard, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dashboard 查询条件
     * @return 总行数
     */
    long count(Dashboard dashboard);

    /**
     * 新增数据
     *
     * @param dashboard 实例对象
     * @return 影响行数
     */
    int insert(Dashboard dashboard);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dashboard> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dashboard> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dashboard> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dashboard> entities);

    /**
     * 修改数据
     *
     * @param dashboard 实例对象
     * @return 影响行数
     */
    int update(Dashboard dashboard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

