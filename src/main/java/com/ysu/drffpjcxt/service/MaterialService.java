package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 物资信息表(Material)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:22
 */
public interface MaterialService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Material queryById(Object id);

    /**
     * 分页查询
     *
     * @param Material   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Material> queryByPage(Material Material, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param Material 实例对象
     * @return 实例对象
     */
    Material insert(Material Material);

    /**
     * 修改数据
     *
     * @param Material 实例对象
     * @return 实例对象
     */
    Material update(Material Material);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
