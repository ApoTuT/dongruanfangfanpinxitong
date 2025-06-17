package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.VerificationAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 核实佐证材料表(VerificationAttachment)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
@Mapper
public interface VerificationAttachmentMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VerificationAttachment queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param VerificationAttachment 查询条件
     * @param pageable                分页对象
     * @return 对象列表
     */
    List<VerificationAttachment> queryAllByLimit(VerificationAttachment VerificationAttachment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tVerificationAttachment 查询条件
     * @return 总行数
     */
    long count(VerificationAttachment tVerificationAttachment);

    /**
     * 新增数据
     *
     * @param tVerificationAttachment 实例对象
     * @return 影响行数
     */
    int insert(VerificationAttachment tVerificationAttachment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VerificationAttachment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VerificationAttachment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VerificationAttachment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VerificationAttachment> entities);

    /**
     * 修改数据
     *
     * @param tVerificationAttachment 实例对象
     * @return 影响行数
     */
    int update(VerificationAttachment tVerificationAttachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

