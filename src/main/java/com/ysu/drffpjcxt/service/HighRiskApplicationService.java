package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.HighRiskApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 高风险标记申请表(HighRiskApplication)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:21
 */
public interface HighRiskApplicationService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HighRiskApplication queryById(Object id);

    /**
     * 分页查询
     *
     * @param highRiskApplication 筛选条件
     * @param pageRequest          分页对象
     * @return 查询结果
     */
    Page<HighRiskApplication> queryByPage(HighRiskApplication highRiskApplication, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param highRiskApplication 实例对象
     * @return 实例对象
     */
    HighRiskApplication insert(HighRiskApplication highRiskApplication);

    /**
     * 修改数据
     *
     * @param highRiskApplication 实例对象
     * @return 实例对象
     */
    HighRiskApplication update(HighRiskApplication highRiskApplication);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);
}
