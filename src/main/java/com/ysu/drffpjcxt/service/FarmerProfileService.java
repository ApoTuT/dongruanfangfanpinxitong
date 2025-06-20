package com.ysu.drffpjcxt.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ysu.drffpjcxt.entity.FarmerProfile;
import com.ysu.drffpjcxt.entity.vo.farmer.FarmerProfileVO;

/**
 * 农户档案表(FarmerProfile)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:20
 */
public interface FarmerProfileService extends IService<FarmerProfile>
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FarmerProfile queryById(Object id);

    /**
     * 分页获取所有农户信息及其风险状态
     * @param page 分页请求参数
     * @return 分页结果
     */
    IPage<FarmerProfileVO> getAllFarmerWithRiskStatus(Page<FarmerProfileVO> page);

}
