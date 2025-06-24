package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.vo.data.DistributionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sheng
 * @date 2025-06-24
 */
@Mapper
public interface DashboardMapper {
    /**
     * 获取监测类型分布
     *
     * @return
     */
    List<DistributionVO> getMonitoringTypeDistribution();

    /**
     * 获取风险等级分布
     *
     * @return
     */
    List<DistributionVO> getRiskLevelDistribution();

    /**
     * 获取地区分布
     *
     * @return
     */
    List<DistributionVO> getRegionDistribution();

    /**
     * 获取农户状态分布
     *
     * @return
     */
    List<DistributionVO> getFarmerStatusDistribution();
}