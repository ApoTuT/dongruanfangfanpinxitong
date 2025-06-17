package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.FarmerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 农户档案表(FarmerProfile)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:20
 */
public interface FarmerProfileService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FarmerProfile queryById(Object id);

    /**
     * 分页查询
     *
     * @param tFarmerProfile 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<FarmerProfile> queryByPage(FarmerProfile FarmerProfile, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param FarmerProfile 实例对象
     * @return 实例对象
     */
    FarmerProfile insert(FarmerProfile FarmerProfile);

    /**
     * 修改数据
     *
     * @param FarmerProfile 实例对象
     * @return 实例对象
     */
    FarmerProfile update(FarmerProfile FarmerProfile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
