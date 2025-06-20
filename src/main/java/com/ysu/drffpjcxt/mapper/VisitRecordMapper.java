package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.VisitRecord;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordQueryDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 走访记录表(VisitRecord)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
@Mapper
public interface VisitRecordMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VisitRecord queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param VisitRecord 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<VisitRecord> queryAllByLimit(VisitRecord VisitRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param VisitRecord 查询条件
     * @return 总行数
     */
    long count(VisitRecord VisitRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VisitRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VisitRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VisitRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VisitRecord> entities);

    /**
     * 修改数据
     *
     * @param VisitRecord 实例对象
     * @return 影响行数
     */
    int update(VisitRecord VisitRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

    /**
     * 新增走访记录
     */
    int insert(VisitRecord visitRecord);

    /**
     * 根据ID软删除走访记录
     */
    int softDeleteById(@Param("recordId") Long recordId, @Param("updateBy") String updateBy);

    /**
     * 根据条件查询走访记录列表
     */
    List<VisitRecordVO> selectByQuery(VisitRecordQueryDTO queryDTO);

}
