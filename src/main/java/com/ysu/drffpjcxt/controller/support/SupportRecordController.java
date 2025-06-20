package com.ysu.drffpjcxt.controller.support;

import com.ysu.drffpjcxt.entity.dto.support.SupportRecordSaveRequest;
import com.ysu.drffpjcxt.service.SupportRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;

/**
 * 帮扶记录管理
 */
@Api(tags = "帮扶记录管理")
@RestController
@PreAuthorize("!hasRole('USER')")
@RequestMapping("/api/support-records")
public class SupportRecordController {

    @Autowired
    private SupportRecordService supportRecordService;

    @PostMapping("/create")
    @ApiOperation("填报帮扶记录")
    public ResponseEntity<?> createSupportRecord(@RequestBody SupportRecordSaveRequest request, Principal principal) {
        try {
            supportRecordService.createSupportRecord(request, principal);
            return ResponseEntity.ok(request.getPlanId() + "计划下的" + request.getMeasureId() + "措施已经更新为已开始");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    @ApiOperation("查询所有帮扶记录")
    public ResponseEntity<?> getAllSupportRecords() {
        return ResponseEntity.ok(supportRecordService.getAllSupportRecords());
    }

    @GetMapping("/select")
    @ApiOperation("根据ID查询帮扶记录")
    public ResponseEntity<?> getSupportRecordById(@RequestParam Long id) {
        return ResponseEntity.ok(supportRecordService.getSupportRecordById(id));
    }
}
