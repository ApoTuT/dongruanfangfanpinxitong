package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.dto.visit.VisitPlanSaveDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitPlanVO;

import java.util.List;

/**
 * 走访计划表(VisitPlan)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:29
 */
public interface VisitPlanService
{
    /**
     * 创建新的走访计划
     * @param saveDTO 包含计划信息和目标农户ID的DTO
     */
    boolean createVisitPlan(VisitPlanSaveDTO saveDTO);

    /**
     * 根据ID删除走访计划
     * @param id 计划ID
     */
    boolean deleteVisitPlan(Long id);

    /**
     * 更新走访计划
     *
     * @param saveDTO 包含计划信息和目标农户ID的DTO
     * @return
     */
    boolean updateVisitPlan(VisitPlanSaveDTO saveDTO);

    /**
     * 根据ID获取走访计划详情
     * @param id 计划ID
     * @return 走访计划的详细视图对象
     */
    VisitPlanVO getVisitPlanById(Long id);

    /**
     * 获取所有走访计划的列表
     * @return 走访计划视图对象的列表
     */
    List<VisitPlanVO> listAllVisitPlans();
}
