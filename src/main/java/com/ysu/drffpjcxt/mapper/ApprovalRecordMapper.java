package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.ApprovalRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 审批记录表(ApprovalRecord)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:17
 */
@Mapper
public interface ApprovalRecordMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ApprovalRecord queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param approvalRecord 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<ApprovalRecord> queryAllByLimit(ApprovalRecord approvalRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param approvalRecord 查询条件
     * @return 总行数
     */
    long count(ApprovalRecord approvalRecord);

    /**
     * 新增数据
     *
     * @param approvalRecord 实例对象
     * @return 影响行数
     */
    int insert(ApprovalRecord approvalRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ApprovalRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ApprovalRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ApprovalRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ApprovalRecord> entities);

    /**
     * 修改数据
     *
     * @param approvalRecord 实例对象
     * @return 影响行数
     */
    int update(ApprovalRecord approvalRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

