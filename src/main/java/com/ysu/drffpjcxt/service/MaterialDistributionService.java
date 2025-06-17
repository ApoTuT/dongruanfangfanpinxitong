package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.MaterialDistribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 物资发放记录表(MaterialDistribution)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:23
 */
public interface MaterialDistributionService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MaterialDistribution queryById(Object id);

    /**
     * 分页查询
     *
     * @param MaterialDistribution 筛选条件
     * @param pageRequest           分页对象
     * @return 查询结果
     */
    Page<MaterialDistribution> queryByPage(MaterialDistribution MaterialDistribution, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param MaterialDistribution 实例对象
     * @return 实例对象
     */
    MaterialDistribution insert(MaterialDistribution MaterialDistribution);

    /**
     * 修改数据
     *
     * @param MaterialDistribution 实例对象
     * @return 实例对象
     */
    MaterialDistribution update(MaterialDistribution MaterialDistribution);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
