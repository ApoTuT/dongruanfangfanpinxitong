package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.SupportRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 帮扶记录表(SupportRecord)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
@Mapper
public interface SupportRecordMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportRecord queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param tSupportRecord 查询条件
     * @param pageable       分页对象
     * @return 对象列表
     */
    List<SupportRecord> queryAllByLimit(SupportRecord tSupportRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param SupportRecord 查询条件
     * @return 总行数
     */
    long count(SupportRecord SupportRecord);

    /**
     * 新增数据
     *
     * @param SupportRecord 实例对象
     * @return 影响行数
     */
    int insert(SupportRecord SupportRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SupportRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SupportRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SupportRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SupportRecord> entities);

    /**
     * 修改数据
     *
     * @param SupportRecord 实例对象
     * @return 影响行数
     */
    int update(SupportRecord SupportRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

