package com.ysu.drffpjcxt.entity.vo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sheng
 * @date 2025-06-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardVO {

    private List<DistributionVO> monitoringTypeDistribution;
    private List<DistributionVO> riskLevelDistribution;
    private List<DistributionVO> regionDistribution;
    private List<DistributionVO> farmerStatusDistribution;

    public List<DistributionVO> getMonitoringTypeDistribution() {
        return monitoringTypeDistribution;
    }

    public void setMonitoringTypeDistribution(List<DistributionVO> monitoringTypeDistribution) {
        this.monitoringTypeDistribution = monitoringTypeDistribution;
    }

    public List<DistributionVO> getRiskLevelDistribution() {
        return riskLevelDistribution;
    }

    public void setRiskLevelDistribution(List<DistributionVO> riskLevelDistribution) {
        this.riskLevelDistribution = riskLevelDistribution;
    }

    public List<DistributionVO> getRegionDistribution() {
        return regionDistribution;
    }

    public void setRegionDistribution(List<DistributionVO> regionDistribution) {
        this.regionDistribution = regionDistribution;
    }

    public List<DistributionVO> getFarmerStatusDistribution() {
        return farmerStatusDistribution;
    }

    public void setFarmerStatusDistribution(List<DistributionVO> farmerStatusDistribution) {
        this.farmerStatusDistribution = farmerStatusDistribution;
    }
}