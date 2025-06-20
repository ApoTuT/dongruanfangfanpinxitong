package com.ysu.drffpjcxt.service;

import com.github.pagehelper.PageInfo;
import com.ysu.drffpjcxt.entity.VisitRecord;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordAddDTO;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordQueryDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitRecordVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 走访记录表(VisitRecord)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:30
 */
public interface VisitRecordService
{
    /**
     * 新增走访记录
     */
    boolean addVisitRecord(VisitRecordAddDTO addDTO);

    /**
     * 软删除走访记录
     */
    boolean deleteVisitRecord(Long recordId);

    /**
     * 查询走访记录
     */
    PageInfo<VisitRecordVO> getVisitRecords(VisitRecordQueryDTO queryDTO);
}
