package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.VisitPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 走访计划表(VisitPlan)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
public interface VisitPlanService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VisitPlan queryById(Object id);

    /**
     * 分页查询
     *
     * @param tVisitPlan  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<VisitPlan> queryByPage(VisitPlan tVisitPlan, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tVisitPlan 实例对象
     * @return 实例对象
     */
    VisitPlan insert(VisitPlan tVisitPlan);

    /**
     * 修改数据
     *
     * @param tVisitPlan 实例对象
     * @return 实例对象
     */
    VisitPlan update(VisitPlan tVisitPlan);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
