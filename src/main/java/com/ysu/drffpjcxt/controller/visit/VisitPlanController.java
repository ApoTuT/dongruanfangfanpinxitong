package com.ysu.drffpjcxt.controller.visit;

import com.ysu.drffpjcxt.entity.dto.visit.VisitPlanSaveDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitPlanVO;
import com.ysu.drffpjcxt.service.VisitPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 走访计划管理 Controller
 */
@RestController
@RequestMapping("/visit/plan")
public class VisitPlanController {

    @Autowired
    private VisitPlanService visitPlanService;

    /**
     * 新增走访计划
     * @param visitPlanSaveDTO 走访计划保存DTO
     * @return 成功响应
     */
    @PostMapping("/")
    public ResponseEntity<Void> createVisitPlan(@RequestBody VisitPlanSaveDTO visitPlanSaveDTO) {
        visitPlanService.createVisitPlan(visitPlanSaveDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据ID删除走访计划
     * @param id 走访计划ID
     * @return 成功响应
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitPlan(@PathVariable Long id) {
        visitPlanService.deleteVisitPlan(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改走访计划
     * @param visitPlanSaveDTO 走访计划保存DTO
     * @return 成功响应
     */
    @PutMapping("/")
    public ResponseEntity<Void> updateVisitPlan(@RequestBody VisitPlanSaveDTO visitPlanSaveDTO) {
        visitPlanService.updateVisitPlan(visitPlanSaveDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据ID查询走访计划详情
     * @param id 走访计划ID
     * @return VisitPlanVO
     */
    @GetMapping("/{id}")
    public ResponseEntity<VisitPlanVO> getVisitPlanById(@PathVariable Long id) {
        VisitPlanVO visitPlanVO = visitPlanService.getVisitPlanById(id);
        return ResponseEntity.ok(visitPlanVO);
    }

    /**
     * 查询所有走访计划列表
     * @return List<VisitPlanVO>
     */
    @GetMapping("/list")
    public ResponseEntity<List<VisitPlanVO>> listAllVisitPlans() {
        List<VisitPlanVO> visitPlanVOs = visitPlanService.listAllVisitPlans();
        return ResponseEntity.ok(visitPlanVOs);
    }
}