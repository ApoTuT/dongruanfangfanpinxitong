package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 报告实例表(ReportInstance)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:25
 */
@Data
public class ReportInstance implements Serializable {
    private static final long serialVersionUID = 281175099983420188L;

    /**
     * 报告实例ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 报告名称
     */
    private String reportName;

    /**
     * 使用的模板ID
     * 【已修改】Object -> Long
     */
    private Long templateId;

    /**
     * 生成参数JSON
     * 【已修改】Object -> String
     */
    private String generationParamsJson;

    /**
     * 生成的报告文件路径
     */
    private String filePath;

    /**
     * 生成人用户ID
     * 【已修改】Object -> Long
     */
    private Long createdBy;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 生成时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}