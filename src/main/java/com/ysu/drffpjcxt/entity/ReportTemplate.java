package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 报告模板表(ReportTemplate)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:25
 */
@Data
public class ReportTemplate implements Serializable {
    private static final long serialVersionUID = -33637296649175268L;

    /**
     * 模板ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板描述
     */
    private String description;

    /**
     * 适用用户层级
     */
    private String applicableLevel;

    /**
     * 报告周期类型
     */
    private String reportCycleType;

    /**
     * 模板文件存储路径
     */
    private String templateFilePath;

    /**
     * 数据源和填充规则JSON配置
     * 【已修改】Object -> String
     */
    private String dataRuleJson;

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