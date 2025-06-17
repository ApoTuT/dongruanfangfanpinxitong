package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 权限表(Permission)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:24
 */
public interface PermissionService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Object id);

    /**
     * 分页查询
     *
     * @param tPermission 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Permission> queryByPage(Permission tPermission, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tPermission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission tPermission);

    /**
     * 修改数据
     *
     * @param tPermission 实例对象
     * @return 实例对象
     */
    Permission update(Permission tPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
