package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.VisitRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 走访记录表(VisitRecord)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:30
 */
public interface VisitRecordService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VisitRecord queryById(Object id);

    /**
     * 分页查询
     *
     * @param tVisitRecord 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<VisitRecord> queryByPage(VisitRecord tVisitRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tVisitRecord 实例对象
     * @return 实例对象
     */
    VisitRecord insert(VisitRecord tVisitRecord);

    /**
     * 修改数据
     *
     * @param tVisitRecord 实例对象
     * @return 实例对象
     */
    VisitRecord update(VisitRecord tVisitRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
