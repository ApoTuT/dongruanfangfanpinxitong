package com.ysu.drffpjcxt.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 仪表盘组件表(DashboardWidget)实体类
 *
 * @author makejava
 * @since 2025-06-12 10:09:19
 */
@Data
public class DashboardWidget implements Serializable {
    private static final long serialVersionUID = -17680696217038977L;

    /**
     * 组件ID
     * 【已修改】Object -> Long
     */
    private Long id;

    /**
     * 仪表盘ID
     * 【已修改】Object -> Long
     */
    private Long dashboardId;

    /**
     * 组件标题
     */
    private String title;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 布局信息JSON
     * 【已修改】Object -> String
     */
    private String layoutJson;

    /**
     * 数据查询配置JSON
     * 【已修改】Object -> String
     */
    private String dataQueryJson;

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