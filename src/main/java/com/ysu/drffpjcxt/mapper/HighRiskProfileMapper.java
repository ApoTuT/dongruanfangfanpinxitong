package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.HighRiskProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 高风险户档案表(HighRiskProfile)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:21
 */
@Mapper
public interface HighRiskProfileMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HighRiskProfile queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param HighRiskProfile 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HighRiskProfile> queryAllByLimit(HighRiskProfile HighRiskProfile, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param HighRiskProfile 查询条件
     * @return 总行数
     */
    long count(HighRiskProfile HighRiskProfile);

    /**
     * 新增数据
     *
     * @param HighRiskProfile 实例对象
     * @return 影响行数
     */
    int insert(HighRiskProfile HighRiskProfile);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HighRiskProfile> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HighRiskProfile> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HighRiskProfile> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HighRiskProfile> entities);

    /**
     * 修改数据
     *
     * @param HighRiskProfile 实例对象
     * @return 影响行数
     */
    int update(HighRiskProfile HighRiskProfile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

