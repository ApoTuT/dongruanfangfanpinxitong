package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.PovertyClue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 贫困线索表(PovertyClue)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:24
 */
@Mapper
public interface PovertyClueMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PovertyClue queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param PovertyClue 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<PovertyClue> queryAllByLimit(PovertyClue PovertyClue, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param PovertyClue 查询条件
     * @return 总行数
     */
    long count(PovertyClue PovertyClue);

    /**
     * 新增数据
     *
     * @param PovertyClue 实例对象
     * @return 影响行数
     */
    int insert(PovertyClue PovertyClue);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PovertyClue> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PovertyClue> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PovertyClue> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PovertyClue> entities);

    /**
     * 修改数据
     *
     * @param PovertyClue 实例对象
     * @return 影响行数
     */
    int update(PovertyClue PovertyClue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

