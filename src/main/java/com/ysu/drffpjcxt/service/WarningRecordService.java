package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.WarningRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 风险预警记录表(WarningRecord)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:31
 */
public interface WarningRecordService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WarningRecord queryById(Object id);

    /**
     * 分页查询
     *
     * @param tWarningRecord 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<WarningRecord> queryByPage(WarningRecord tWarningRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tWarningRecord 实例对象
     * @return 实例对象
     */
    WarningRecord insert(WarningRecord tWarningRecord);

    /**
     * 修改数据
     *
     * @param tWarningRecord 实例对象
     * @return 实例对象
     */
    WarningRecord update(WarningRecord tWarningRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
