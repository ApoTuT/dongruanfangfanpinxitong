package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.ClueVerification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 线索核实记录表(ClueVerification)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:18
 */
public interface ClueVerificationService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClueVerification queryById(Object id);

    /**
     * 分页查询
     *
     * @param ClueVerification 筛选条件
     * @param pageRequest       分页对象
     * @return 查询结果
     */
    Page<ClueVerification> queryByPage(ClueVerification ClueVerification, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param ClueVerification 实例对象
     * @return 实例对象
     */
    ClueVerification insert(ClueVerification ClueVerification);

    /**
     * 修改数据
     *
     * @param ClueVerification 实例对象
     * @return 实例对象
     */
    ClueVerification update(ClueVerification ClueVerification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
