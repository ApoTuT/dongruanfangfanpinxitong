package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 高风险户档案表(HighRiskProfile)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:21
 */
@Data
public class HighRiskProfile implements Serializable {
    private static final long serialVersionUID = -23392398747975929L;

    /**
     * 高风险档案ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 关联高风险标记申请ID
     * 【已修改】Object -> Long
     */
    private Long markingApplicationId;

    /**
     * 风险原因详细描述
     */
    private String riskCauseDescription;

    /**
     * 风险发展趋势描述
     */
    private String riskTrendDescription;

    /**
     * 监测状态:1-重点监测,2-常规监测,3-观察期
     * 【已修改】Boolean -> Integer
     */
    private Integer monitoringStatus;

    /**
     * 首次识别为高风险的日期
     */
    private Date riskIdentifiedDate;

    /**
     * 最近一次预警时间
     */
    private Date lastWarningTime;

    /**
     * 累计预警次数
     * 【已修改】Object -> Integer
     */
    private Integer totalWarningCount;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}