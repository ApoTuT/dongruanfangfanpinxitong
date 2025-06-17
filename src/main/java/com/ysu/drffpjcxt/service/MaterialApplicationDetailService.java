package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.MaterialApplicationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 物资申请明细表(MaterialApplicationDetail)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
public interface MaterialApplicationDetailService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MaterialApplicationDetail queryById(Object id);

    /**
     * 分页查询
     *
     * @param tMaterialApplicationDetail 筛选条件
     * @param pageRequest                分页对象
     * @return 查询结果
     */
    Page<MaterialApplicationDetail> queryByPage(MaterialApplicationDetail MaterialApplicationDetail, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param MaterialApplicationDetail 实例对象
     * @return 实例对象
     */
    MaterialApplicationDetail insert(MaterialApplicationDetail MaterialApplicationDetail);

    /**
     * 修改数据
     *
     * @param MaterialApplicationDetail 实例对象
     * @return 实例对象
     */
    MaterialApplicationDetail update(MaterialApplicationDetail MaterialApplicationDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
