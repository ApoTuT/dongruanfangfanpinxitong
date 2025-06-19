package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.SupportPlan;
import com.ysu.drffpjcxt.entity.dto.support.SupportPlanSaveRequest;
import com.ysu.drffpjcxt.entity.vo.support.SupportPlanDetailVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.security.Principal;

/**
 * 帮扶计划表(SupportPlan)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
public interface SupportPlanService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportPlan queryById(Object id);

    /**
     * 分页查询
     *
     * @param tSupportPlan 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<SupportPlan> queryByPage(SupportPlan tSupportPlan, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tSupportPlan 实例对象
     * @return 实例对象
     */
    SupportPlan insert(SupportPlan tSupportPlan);

    /**
     * 修改数据
     *
     * @param tSupportPlan 实例对象
     * @return 实例对象
     */
    SupportPlan update(SupportPlan tSupportPlan);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);
    /**
     * 新增一个完整的帮扶计划，包括其下的所有帮扶措施。
     *
     * @param request 包含计划和措施详情的请求对象
     * @param principal Spring Security 提供的凭证对象，用于获取当前操作用户ID
     * @return 创建成功后的帮扶计划ID
     */
    Long createSupportPlan(SupportPlanSaveRequest request, Principal principal);
    /**
     * (新增) 根据ID查询帮扶计划的详细信息，包括所有帮扶措施。
     * @param id 计划ID
     * @return 计划详情，如果不存在则返回null
     */
    SupportPlanDetailVO getPlanDetailById(Long id);

    /**
     * (新增) 更新一个完整的帮扶计划。
     * @param planId 要更新的计划ID
     * @param request 包含最新计划和措施信息的请求对象
     * @param principal 当前操作用户
     * @return 更新后的计划ID
     */
    Long updateSupportPlan(Long planId, SupportPlanSaveRequest request, Principal principal);

    /**
     * (新增) 根据ID删除一个帮扶计划（逻辑删除）。
     * @param id 计划ID
     */
    void deleteSupportPlanById(Long id);

}
