package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.MaterialApplicationDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 物资申请明细表(MaterialApplicationDetail)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
@Mapper
public interface MaterialApplicationDetailMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
   MaterialApplicationDetail queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param materialApplicationDetail 查询条件
     * @param pageable                   分页对象
     * @return 对象列表
     */
    List<MaterialApplicationDetail> queryAllByLimit(MaterialApplicationDetail materialApplicationDetail, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param materialApplicationDetail 查询条件
     * @return 总行数
     */
    long count(MaterialApplicationDetail materialApplicationDetail);

    /**
     * 新增数据
     *
     * @param materialApplicationDetail 实例对象
     * @return 影响行数
     */
    int insert(MaterialApplicationDetail materialApplicationDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MaterialApplicationDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MaterialApplicationDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MaterialApplicationDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MaterialApplicationDetail> entities);

    /**
     * 修改数据
     *
     * @param materialApplicationDetail 实例对象
     * @return 影响行数
     */
    int update(MaterialApplicationDetail materialApplicationDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

