package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.FamilyMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 家庭成员表(FamilyMember)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:19
 */
@Mapper
public interface FamilyMemberMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FamilyMember queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param familyMember 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<FamilyMember> queryAllByLimit(FamilyMember familyMember, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param familyMember 查询条件
     * @return 总行数
     */
    long count(FamilyMember familyMember);

    /**
     * 新增数据
     *
     * @param familyMember 实例对象
     * @return 影响行数
     */
    int insert(FamilyMember familyMember);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FamilyMember> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FamilyMember> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FamilyMember> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<FamilyMember> entities);

    /**
     * 修改数据
     *
     * @param familyMember 实例对象
     * @return 影响行数
     */
    int update(FamilyMember familyMember);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

