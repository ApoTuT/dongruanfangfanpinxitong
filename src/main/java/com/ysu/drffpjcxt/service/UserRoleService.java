package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户角色关联表(UserRole)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
public interface UserRoleService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserRole queryById(Object id);

    /**
     * 分页查询
     *
     * @param tUserRole   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UserRole> queryByPage(UserRole tUserRole, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole tUserRole);

    /**
     * 修改数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole tUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
