package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户基础信息表(User)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 550526848760585999L;

    /**
     * 用户唯一ID
     */
    private Long id;

    /**
     * 员工工号
     */
    private String employeeId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码(登录账号)
     */
    private String phone;

    /**
     * 加密后的登录密码
     */
    private String password;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 省级行政区划代码
     */
    private String provinceCode;

    /**
     * 市级行政区划代码
     */
    private String cityCode;

    /**
     * 县级行政区划代码
     */
    private String countyCode;

    /**
     * 乡级行政区划代码
     */
    private String townshipCode;

    /**
     * 村级行政区划代码
     */
    private String villageCode;

    /**
     * 所属单位代码
     */
    private String departmentCode;

    /**
     * 所属单位名称
     */
    private String departmentName;

    /**
     * 账户状态:0-禁用,1-启用,2-待审批,3-已锁定
     */
    private Integer status;

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