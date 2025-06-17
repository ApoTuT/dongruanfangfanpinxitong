package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 家庭成员表(FamilyMember)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:19
 */
@Data
public class FamilyMember implements Serializable {
    private static final long serialVersionUID = -62958761277773156L;

    /**
     * 家庭成员ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联农户档案ID
     * 【已修改】Object -> Long
     */
    private Long farmerId;

    /**
     * 成员姓名
     */
    private String memberName;

    /**
     * 与户主关系
     */
    private String relationToHead;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别:1-男,2-女
     * 【已修改】Boolean -> Integer
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 健康状况
     */
    private String healthStatus;

    /**
     * 劳动能力
     */
    private String laborAbility;

    /**
     * 文化程度
     */
    private String educationLevel;

    /**
     * 在校情况
     */
    private String schoolStatus;

    /**
     * 务工情况
     */
    private String employmentStatus;

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