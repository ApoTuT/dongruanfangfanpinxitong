package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 核实佐证材料表(VerificationAttachment)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
@Data
public class VerificationAttachment implements Serializable {
    private static final long serialVersionUID = -93496496271697791L;

    /**
     * 附件ID
     */
    private Long id;

    /**
     * 关联核实记录ID
     */
    private Long verificationId;

    /**
     * 附件类型
     */
    private String attachmentType;

    /**
     * 原始文件名
     */
    private String originalFileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件大小(字节)
     */
    private Long fileSize;

    /**
     * 上传人用户ID
     */
    private String uploadedBy;

    /**
     * 逻辑删除标记:0-未删除,1-已删除
     */
    private Boolean isDeleted;

    /**
     * 上传时间
     */
    private Date uploadedAt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}