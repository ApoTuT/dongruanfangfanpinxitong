package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 角色权限关联表(RolePermission)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
public interface RolePermissionService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RolePermission queryById(Object id);

    /**
     * 分页查询
     *
     * @param tRolePermission 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<RolePermission> queryByPage(RolePermission tRolePermission, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tRolePermission 实例对象
     * @return 实例对象
     */
    RolePermission insert(RolePermission tRolePermission);

    /**
     * 修改数据
     *
     * @param tRolePermission 实例对象
     * @return 实例对象
     */
    RolePermission update(RolePermission tRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
