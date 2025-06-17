package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.MaterialApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 物资申请单表(MaterialApplication)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
public interface MaterialApplicationService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MaterialApplication queryById(Object id);

    /**
     * 分页查询
     *
     * @param MaterialApplication 筛选条件
     * @param pageRequest          分页对象
     * @return 查询结果
     */
    Page<MaterialApplication> queryByPage(MaterialApplication MaterialApplication, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param MaterialApplication 实例对象
     * @return 实例对象
     */
    MaterialApplication insert(MaterialApplication MaterialApplication);

    /**
     * 修改数据
     *
     * @param MaterialApplication 实例对象
     * @return 实例对象
     */
    MaterialApplication update(MaterialApplication MaterialApplication);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
