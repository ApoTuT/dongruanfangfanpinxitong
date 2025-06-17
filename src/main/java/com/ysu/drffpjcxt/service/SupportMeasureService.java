package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.SupportMeasure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 帮扶措施表(SupportMeasure)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:26
 */
public interface SupportMeasureService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportMeasure queryById(Object id);

    /**
     * 分页查询
     *
     * @param tSupportMeasure 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<SupportMeasure> queryByPage(SupportMeasure tSupportMeasure, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tSupportMeasure 实例对象
     * @return 实例对象
     */
    SupportMeasure insert(SupportMeasure tSupportMeasure);

    /**
     * 修改数据
     *
     * @param tSupportMeasure 实例对象
     * @return 实例对象
     */
    SupportMeasure update(SupportMeasure tSupportMeasure);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
