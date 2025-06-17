package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典表(DataDictionary)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:19
 */
@Data
public class DataDictionary implements Serializable {
    private static final long serialVersionUID = 956923155989264291L;

    /**
     * 字典ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 字典类型编码
     */
    private String typeCode;

    /**
     * 字典项编码
     */
    private String itemCode;

    /**
     * 字典项名称
     */
    private String itemName;

    /**
     * 排序号
     * 【已修改】Object -> Integer
     */
    private Integer sortOrder;

    /**
     * 是否启用:0-禁用,1-启用
     */
    private Boolean isEnabled;

    /**
     * 描述
     */
    private String description;

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