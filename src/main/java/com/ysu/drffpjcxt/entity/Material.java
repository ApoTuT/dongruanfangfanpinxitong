package com.ysu.drffpjcxt.entity;


import java.util.Date;
import java.io.Serializable;

/**
 * 物资信息表(Material)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:21
 */
public class Material implements Serializable
{
    private static final long serialVersionUID = 654095878345521330L;
    /**
     * 物资ID
     */
    private Object id;
    /**
     * 物资名称
     */
    private String materialName;
    /**
     * 规格型号
     */
    private String specification;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 物资描述
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


    public Object getId()
    {
        return id;
    }

    public void setId(Object id)
    {
        this.id = id;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getSpecification()
    {
        return specification;
    }

    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

}

