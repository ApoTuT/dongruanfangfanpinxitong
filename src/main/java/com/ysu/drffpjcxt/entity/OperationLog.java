package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志表(OperationLog)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:23
 */
@Data
public class OperationLog implements Serializable {
    private static final long serialVersionUID = 718394782886456482L;

    /**
     * 日志ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 操作人用户ID
     * 【已修改】Object -> Long
     */
    private Long operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 操作详细描述
     */
    private String description;

    /**
     * 请求IP地址
     */
    private String requestIp;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 操作是否成功:0-失败,1-成功
     */
    private Boolean isSuccess;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}