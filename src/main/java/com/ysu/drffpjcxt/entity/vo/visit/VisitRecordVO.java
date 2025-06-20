package com.ysu.drffpjcxt.entity.vo.visit;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 走访记录VO
 */
@Data
public class VisitRecordVO {

    private Integer recordId;
    private Integer planId;
    private Integer farmerId;
    private String farmerName; // 关联查询出的农户姓名
    private LocalDateTime visitDate;
    private String visitLocation;
    private String visitContent;
    private String feedback;
    private LocalDateTime createTime;
    private String createBy;
}