package com.ysu.drffpjcxt.controller.visit;

import com.github.pagehelper.PageInfo;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordAddDTO;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordQueryDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitRecordVO;
import com.ysu.drffpjcxt.service.VisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visit")
@PreAuthorize("!hasRole('USER')")
public class VisitRecordController {

    @Autowired
    private VisitRecordService visitRecordService;

    /**
     * 新增走访记录
     */
    @PostMapping("/add")
    public ResponseEntity<String> addVisitRecord(@RequestBody VisitRecordAddDTO addDTO) {
        boolean success = visitRecordService.addVisitRecord(addDTO);
        if (!success) {
            return ResponseEntity.badRequest().body("新增走访记录失败，请检查输入数据是否正确");
        }
        return ResponseEntity.ok("新增走访记录成功");
    }

    /**
     * 删除走访记录（软删除）
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteVisitRecord(@RequestParam("id") Long recordId) {
        boolean success = visitRecordService.deleteVisitRecord(recordId);
        if (!success) {
            return ResponseEntity.badRequest().body("删除走访记录失败，请检查输入数据是否正确");
        }
        return ResponseEntity.ok("删除走访记录成功");
    }

    /**
     * 分页查询走访记录
     */
    @PostMapping("/list")
    public ResponseEntity<PageInfo<VisitRecordVO>> getVisitRecords(@RequestBody VisitRecordQueryDTO queryDTO) {
        PageInfo<VisitRecordVO> pageInfo = visitRecordService.getVisitRecords(queryDTO);
        return ResponseEntity.ok(pageInfo);
    }
}