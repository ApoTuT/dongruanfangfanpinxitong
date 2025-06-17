package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.Dashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 仪表盘表(Dashboard)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:18
 */
public interface DashboardService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dashboard queryById(Object id);

    /**
     * 分页查询
     *
     * @param Dashboard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Dashboard> queryByPage(Dashboard Dashboard, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param Dashboard 实例对象
     * @return 实例对象
     */
    Dashboard insert(Dashboard Dashboard);

    /**
     * 修改数据
     *
     * @param Dashboard 实例对象
     * @return 实例对象
     */
    Dashboard update(Dashboard Dashboard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
