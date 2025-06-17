package com.ysu.drffpjcxt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 行政区划表(AdministrativeDivision)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:12
 */
@Data
public class AdministrativeDivision implements Serializable
{
    private static final long serialVersionUID = 606881396842754927L;
    /**
     * 行政区划代码
     */
    private String code;
    /**
     * 行政区划名称
     */
    private String divisionName;
    /**
     * 级别:1-省,2-市,3-县,4-乡,5-村
     */
    private Integer levelType;
    /**
     * 上级行政区划代码
     */
    private String parentCode;
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

