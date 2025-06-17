package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色权限关联表(RolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
@Mapper
public interface RolePermissionMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RolePermission queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param RolePermission 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<RolePermission> queryAllByLimit(RolePermission RolePermission, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param RolePermission 查询条件
     * @return 总行数
     */
    long count(RolePermission RolePermission);

    /**
     * 新增数据
     *
     * @param RolePermission 实例对象
     * @return 影响行数
     */
    int insert(RolePermission RolePermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RolePermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RolePermission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RolePermission> entities);

    /**
     * 修改数据
     *
     * @param tRolePermission 实例对象
     * @return 影响行数
     */
    int update(RolePermission tRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

