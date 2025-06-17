package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 农户档案表(FarmerProfile)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:20
 */
@Data
public class FarmerProfile implements Serializable {
    private static final long serialVersionUID = 148239178325477934L;

    /**
     * 农户档案ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 户主姓名
     */
    private String headName;

    /**
     * 户主身份证号
     */
    private String headIdCard;

    /**
     * 家庭住址行政区划代码
     */
    private String addressCode;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 家庭人口数量
     * 【已修改】Object -> Integer
     */
    private Integer populationCount;

    /**
     * 监测对象类型:1-脱贫不稳定户,2-边缘易致贫户,3-突发严重困难户
     * 【已修改】Boolean -> Integer
     */
    private Integer monitoringType;

    /**
     * 风险等级:1-高风险,2-中风险,3-低风险
     * 【已修改】Boolean -> Integer
     */
    private Integer riskLevel;

    /**
     * 主要风险类型(JSON数组)
     * 【已修改】Object -> String
     */
    private String mainRiskTypes;

    /**
     * 经济状况信息(JSON)
     * 【已修改】Object -> String
     */
    private String economicInfoJson;

    /**
     * 住房与生活条件信息(JSON)
     * 【已修改】Object -> String
     */
    private String housingInfoJson;

    /**
     * 享受政策与帮扶情况(JSON)
     * 【已修改】Object -> String
     */
    private String policyInfoJson;

    /**
     * 档案状态:1-正常监测,2-已解除风险
     * 【已修改】Boolean -> Integer
     */
    private Integer status;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 创建人用户ID
     * 【已修改】Object -> Long
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新人用户ID
     * 【已修改】Object -> Long
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private Date updateTime;
}