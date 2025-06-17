package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.ReportTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 报告模板表(ReportTemplate)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:25
 */
public interface ReportTemplateService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReportTemplate queryById(Object id);

    /**
     * 分页查询
     *
     * @param tReportTemplate 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<ReportTemplate> queryByPage(ReportTemplate tReportTemplate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tReportTemplate 实例对象
     * @return 实例对象
     */
    ReportTemplate insert(ReportTemplate tReportTemplate);

    /**
     * 修改数据
     *
     * @param tReportTemplate 实例对象
     * @return 实例对象
     */
    ReportTemplate update(ReportTemplate tReportTemplate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
