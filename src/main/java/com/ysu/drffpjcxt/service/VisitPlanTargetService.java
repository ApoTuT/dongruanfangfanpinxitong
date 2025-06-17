package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.VisitPlanTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 走访计划目标表(VisitPlanTarget)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
public interface VisitPlanTargetService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VisitPlanTarget queryById(Object id);

    /**
     * 分页查询
     *
     * @param tVisitPlanTarget 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VisitPlanTarget> queryByPage(VisitPlanTarget tVisitPlanTarget, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tVisitPlanTarget 实例对象
     * @return 实例对象
     */
    VisitPlanTarget insert(VisitPlanTarget tVisitPlanTarget);

    /**
     * 修改数据
     *
     * @param tVisitPlanTarget 实例对象
     * @return 实例对象
     */
    VisitPlanTarget update(VisitPlanTarget tVisitPlanTarget);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
