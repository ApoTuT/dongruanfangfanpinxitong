package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 数据字典表(DataDictionary)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:19
 */
@Mapper
public interface DataDictionaryMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DataDictionary queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param dataDictionary 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<DataDictionary> queryAllByLimit(DataDictionary dataDictionary, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dataDictionary 查询条件
     * @return 总行数
     */
    long count(DataDictionary dataDictionary);

    /**
     * 新增数据
     *
     * @param dataDictionary 实例对象
     * @return 影响行数
     */
    int insert(DataDictionary dataDictionary);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DataDictionary> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DataDictionary> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DataDictionary> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DataDictionary> entities);

    /**
     * 修改数据
     *
     * @param dataDictionary 实例对象
     * @return 影响行数
     */
    int update(DataDictionary dataDictionary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

