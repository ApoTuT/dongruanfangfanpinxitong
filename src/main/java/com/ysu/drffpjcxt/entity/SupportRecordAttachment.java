package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 帮扶记录附件表(SupportRecordAttachment)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
@Data
public class SupportRecordAttachment implements Serializable {
    private static final long serialVersionUID = 378684580013226040L;

    /**
     * 附件ID
     */
    private Long id;

    /**
     * 关联帮扶记录ID
     */
    private Long recordId;

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
