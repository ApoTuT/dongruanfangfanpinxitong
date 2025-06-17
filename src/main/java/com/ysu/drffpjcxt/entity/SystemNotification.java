package com.ysu.drffpjcxt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统通知表(SystemNotification)实体类
 */
@Data
public class SystemNotification implements Serializable {
    private static final long serialVersionUID = -48678049859041290L;

    /**
     * 通知ID
     */
    private Long id;

    /**
     * 接收人用户ID
     */
    private Long recipientId;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知类型
     */
    private String notificationType;

    /**
     * 关联业务ID
     */
    private String businessId;

    /**
     * 关联业务类型
     */
    private String businessType;

    /**
     * 是否已读:0-未读,1-已读
     */
    private Boolean isRead;

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