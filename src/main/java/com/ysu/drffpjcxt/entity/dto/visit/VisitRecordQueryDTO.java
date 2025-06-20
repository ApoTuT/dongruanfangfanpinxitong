package com.ysu.drffpjcxt.entity.dto.visit;

import lombok.Data;

/**
 * 查询走访记录DTO
 */
@Data
public class VisitRecordQueryDTO {
    // 用于分页
    private int pageNum = 1;
    private int pageSize = 10;

    // 查询条件
    private Integer farmerId; // 根据农户ID查询
    private String farmerName; // 根据农户姓名模糊查询
    private Integer visitorId; // 根据访问者用户ID查询
    private String userName; // 根据访问者姓名模糊查询
}