package com.ysu.drffpjcxt.service.impl.data;

import com.ysu.drffpjcxt.entity.vo.data.DashboardVO;
import com.ysu.drffpjcxt.mapper.DashboardMapper;
import com.ysu.drffpjcxt.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 
 * @date 2025-06-24
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public DashboardVO getDashboardStatus() {
        DashboardVO dashboardVO = new DashboardVO();
        dashboardVO.setMonitoringTypeDistribution(dashboardMapper.getMonitoringTypeDistribution());
        dashboardVO.setRiskLevelDistribution(dashboardMapper.getRiskLevelDistribution());
        dashboardVO.setRegionDistribution(dashboardMapper.getRegionDistribution());
        dashboardVO.setFarmerStatusDistribution(dashboardMapper.getFarmerStatusDistribution());
        return dashboardVO;
    }
}