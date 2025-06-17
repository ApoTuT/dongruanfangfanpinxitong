package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 申请佐证材料表(ApplicationAttachment)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:17
 */
@Data
public class ApplicationAttachment implements Serializable {
    private static final long serialVersionUID = 859192699461615934L;

    /**
     * 附件ID
     *【已修改】Object -> Long
     */
    private Long id;

    /**
     * 关联申请ID
     *【已修改】Object -> Long
     */
    private Long applicationId;

    /**
     * 原始文件名
     */
    private String originalFileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小(字节)
     *【已修改】Object -> Long
     */
    private Long fileSize;

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