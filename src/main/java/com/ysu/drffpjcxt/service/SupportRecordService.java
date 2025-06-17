package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.SupportRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 帮扶记录表(SupportRecord)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
public interface SupportRecordService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportRecord queryById(Object id);

    /**
     * 分页查询
     *
     * @param tSupportRecord 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<SupportRecord> queryByPage(SupportRecord tSupportRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tSupportRecord 实例对象
     * @return 实例对象
     */
    SupportRecord insert(SupportRecord tSupportRecord);

    /**
     * 修改数据
     *
     * @param tSupportRecord 实例对象
     * @return 实例对象
     */
    SupportRecord update(SupportRecord tSupportRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
