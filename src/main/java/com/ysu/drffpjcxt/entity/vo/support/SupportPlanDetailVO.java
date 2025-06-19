package com.ysu.drffpjcxt.entity.vo.support;

import com.ysu.drffpjcxt.entity.SupportMeasure;
import com.ysu.drffpjcxt.entity.SupportPlan;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于展示帮扶计划详情的数据视图对象
 */
@Data
@NoArgsConstructor
public class SupportPlanDetailVO {

    // 包含所有 SupportPlan 的字段
    private SupportPlan planInfo;
    
    // 包含该计划下的所有措施
    private List<SupportMeasure> measures;
    
    // 可以补充一些关联查询出的信息，比如制定人名称
    private String plannerName;
    private String farmerName;

    public SupportPlanDetailVO(SupportPlan plan, List<SupportMeasure> measures) {
        this.planInfo = plan;
        this.measures = measures;
    }
}