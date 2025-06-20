package com.ysu.drffpjcxt.controller.support;

import com.ysu.drffpjcxt.entity.dto.support.ApprovalRequest;
import com.ysu.drffpjcxt.entity.dto.support.SupportPlanSaveRequest;
import com.ysu.drffpjcxt.entity.vo.support.SupportPlanDetailVO;
import com.ysu.drffpjcxt.service.SupportPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

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
    public ResponseEntity<?> getPlanDetail() {
        List<SupportPlanDetailVO> detail = supportPlanService.getAllPlanDetail();
        return detail != null ? ResponseEntity.ok(detail) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新帮扶计划")
    public ResponseEntity<?> updateSupportPlan(@PathVariable Long id,
                                               @RequestBody SupportPlanSaveRequest request,
                                               Principal principal) {
        try {
            Long planId = supportPlanService.updateSupportPlan(id, request, principal);
            return ResponseEntity.ok(Collections.singletonMap("planId", planId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除帮扶计划")
    public ResponseEntity<?> deleteSupportPlan(@PathVariable Long id) {
        try {
            supportPlanService.deleteSupportPlanById(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "删除成功"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/approval/{id}")
    @ApiOperation("审批帮扶计划 (需要县级干部权限)")
    public ResponseEntity<?> approvePlan(@PathVariable Long id,
                                         @RequestBody ApprovalRequest request,
                                         Principal principal) {
        try {
            supportPlanService.approvePlan(id, request, principal);
            return ResponseEntity.ok(Collections.singletonMap("message", "审批操作成功"));
        } catch (IllegalArgumentException e) {
            // 处理断言失败，如计划不存在或状态不正确
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AccessDeniedException e) {
            // 由Spring Security自动抛出，处理权限不足的情况
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("权限不足，无法执行审批操作");
        }
    }
}