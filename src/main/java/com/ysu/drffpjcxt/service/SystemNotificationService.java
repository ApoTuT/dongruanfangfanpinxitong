package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.SystemNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 系统通知表(SystemNotification)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
public interface SystemNotificationService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemNotification queryById(Object id);

    /**
     * 分页查询
     *
     * @param tSystemNotification 筛选条件
     * @param pageRequest         分页对象
     * @return 查询结果
     */
    Page<SystemNotification> queryByPage(SystemNotification tSystemNotification, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tSystemNotification 实例对象
     * @return 实例对象
     */
    SystemNotification insert(SystemNotification tSystemNotification);

    /**
     * 修改数据
     *
     * @param tSystemNotification 实例对象
     * @return 实例对象
     */
    SystemNotification update(SystemNotification tSystemNotification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
