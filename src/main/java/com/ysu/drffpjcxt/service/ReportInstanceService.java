package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.ReportInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 报告实例表(ReportInstance)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:25
 */
public interface ReportInstanceService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReportInstance queryById(Object id);

    /**
     * 分页查询
     *
     * @param tReportInstance 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<ReportInstance> queryByPage(ReportInstance tReportInstance, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tReportInstance 实例对象
     * @return 实例对象
     */
    ReportInstance insert(ReportInstance tReportInstance);

    /**
     * 修改数据
     *
     * @param tReportInstance 实例对象
     * @return 实例对象
     */
    ReportInstance update(ReportInstance tReportInstance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
