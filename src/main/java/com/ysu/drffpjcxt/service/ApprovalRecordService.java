package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.ApprovalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 审批记录表(ApprovalRecord)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:17
 */
public interface ApprovalRecordService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ApprovalRecord queryById(Object id);

    /**
     * 分页查询
     *
     * @param ApprovalRecord 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<ApprovalRecord> queryByPage(ApprovalRecord ApprovalRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param ApprovalRecord 实例对象
     * @return 实例对象
     */
    ApprovalRecord insert(ApprovalRecord ApprovalRecord);

    /**
     * 修改数据
     *
     * @param ApprovalRecord 实例对象
     * @return 实例对象
     */
    ApprovalRecord update(ApprovalRecord ApprovalRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
