package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 角色表(Role)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
public interface RoleService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Object id);

    /**
     * 分页查询
     *
     * @param tRole       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Role> queryByPage(Role tRole, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    Role insert(Role tRole);

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    Role update(Role tRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
