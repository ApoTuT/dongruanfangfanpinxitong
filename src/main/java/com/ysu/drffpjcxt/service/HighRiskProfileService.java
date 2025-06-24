package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.HighRiskProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 高风险户档案表(HighRiskProfile)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:21
 */
public interface HighRiskProfileService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HighRiskProfile queryById(Object id);

    /**
     * 分页查询
     *
     * @param HighRiskProfile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HighRiskProfile> queryByPage(HighRiskProfile HighRiskProfile, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param HighRiskProfile 实例对象
     * @return 实例对象
     */
    HighRiskProfile insert(HighRiskProfile HighRiskProfile);

    /**
     * 修改数据
     *
     * @param HighRiskProfile 实例对象
     * @return 实例对象
     */
    HighRiskProfile update(HighRiskProfile HighRiskProfile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

    /**
     * 查询所有高风险档案
     * @param status 状态筛选条件，传"待审批"则只查询待审批的记录，传null或空字符串则查询所有
     * @return 高风险档案列表
     */
    List<HighRiskProfile> selectAll(String status);
    /**
     * 审批操作
     * @param id 档案ID
     * @param status 审批结果: "通过" 或 "不通过"
     */
    void approve(Long id, String status);
}
