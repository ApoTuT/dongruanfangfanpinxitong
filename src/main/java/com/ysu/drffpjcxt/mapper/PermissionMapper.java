package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:24
 */
@Mapper
public interface PermissionMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param Permission 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(Permission Permission, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param Permission 查询条件
     * @return 总行数
     */
    long count(Permission Permission);

    /**
     * 新增数据
     *
     * @param tPermission 实例对象
     * @return 影响行数
     */
    int insert(Permission tPermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Permission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Permission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Permission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Permission> entities);

    /**
     * 修改数据
     *
     * @param Permission 实例对象
     * @return 影响行数
     */
    int update(Permission Permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

