package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.OperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 操作日志表(OperationLog)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:23
 */
public interface OperationLogService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OperationLog queryById(Object id);

    /**
     * 分页查询
     *
     * @param OperationLog 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<OperationLog> queryByPage(OperationLog OperationLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param OperationLog 实例对象
     * @return 实例对象
     */
    OperationLog insert(OperationLog OperationLog);

    /**
     * 修改数据
     *
     * @param OperationLog 实例对象
     * @return 实例对象
     */
    OperationLog update(OperationLog OperationLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
