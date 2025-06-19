package com.ysu.drffpjcxt.controller.support;

import com.ysu.drffpjcxt.entity.dto.support.SupportPlanSaveRequest;
import com.ysu.drffpjcxt.entity.vo.support.SupportPlanDetailVO;
import com.ysu.drffpjcxt.service.SupportPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;

@Api(tags = "帮扶计划管理")
@RestController
@RequestMapping("/api/support-plans")
public class SupportPlanController {

    @Autowired
    private SupportPlanService supportPlanService;

    @PostMapping
    @ApiOperation("创建新的帮扶计划")
    public ResponseEntity<?> createSupportPlan(@RequestBody SupportPlanSaveRequest request, Principal principal) {
        try {
            Long planId = supportPlanService.createSupportPlan(request, principal);
            return ResponseEntity.ok(Collections.singletonMap("planId", planId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail")
    @ApiOperation("查询帮扶计划详情")
    public ResponseEntity<?> getPlanDetail(@RequestParam Long id) {
        SupportPlanDetailVO detail = supportPlanService.getPlanDetailById(id);
        return detail != null ? ResponseEntity.ok(detail) : ResponseEntity.notFound().build();
    }

    @GetMapping("/update")
    @ApiOperation("更新帮扶计划")
    public ResponseEntity<?> updateSupportPlan(@RequestParam Long id,
                                               @RequestBody SupportPlanSaveRequest request,
                                               Principal principal) {
        try {
            Long planId = supportPlanService.updateSupportPlan(id, request, principal);
            return ResponseEntity.ok(Collections.singletonMap("planId", planId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/delete")
    @ApiOperation("删除帮扶计划")
    public ResponseEntity<?> deleteSupportPlan(@RequestParam Long id) {
        try {
            supportPlanService.deleteSupportPlanById(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "删除成功"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}