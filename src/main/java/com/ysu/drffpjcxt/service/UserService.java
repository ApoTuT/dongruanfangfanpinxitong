package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户基础信息表(User)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
public interface UserService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Object id);

    /**
     * 分页查询
     *
     * @param tUser       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User tUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    User insert(User tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    User update(User tUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
