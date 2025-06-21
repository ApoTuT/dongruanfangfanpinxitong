package com.ysu.drffpjcxt.controller.risk;

import com.ysu.drffpjcxt.entity.HighRiskProfile;
import com.ysu.drffpjcxt.entity.dto.risk.HighRiskApplicationRequest;
import com.ysu.drffpjcxt.entity.vo.risk.HighRiskApplicationVo;
import com.ysu.drffpjcxt.service.HighRiskProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * 高风险户档案管理
 * 仅操作 t_high_risk_profile 表
 */
@RestController
@RequestMapping("/api/farmer/high-risk-profile")
@PreAuthorize("!hasRole('USER')")
public class HighRiskProfileController {
    @Resource
    private HighRiskProfileService highRiskProfileService;

    /**
     * 创建高风险档案
     */
    @PostMapping("/create")
    public ResponseEntity<HighRiskApplicationVo> create(@RequestBody HighRiskApplicationRequest req) {
        HighRiskProfile profile = new HighRiskProfile();
        profile.setFarmerId(req.getFarmerId().longValue());
        profile.setRiskCauseDescription(req.getRiskCauseDescription());
        profile.setRiskTrendDescription(req.getRiskTrendDescription());
        if (req.getMonitoringStatus() != null) {
            profile.setMonitoringStatus(Integer.parseInt(req.getMonitoringStatus()));
        }
        profile.setRiskIdentifiedDate(req.getRiskIdentifiedDate());
        profile.setLastWarningTime(req.getLastWarningTime());
        profile.setTotalWarningCount(req.getTotalWarningCount());
        profile.setStatus(req.getStatus());
        HighRiskProfile saved = highRiskProfileService.insert(profile);
        HighRiskApplicationVo vo = toVo(saved);
        return ResponseEntity.ok(vo);
    }

    /**
     * 查询高风险档案列表
     */
    @GetMapping("select")
    public ResponseEntity<Map<String,Object>> list(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size) {
        Page<HighRiskProfile> pr = highRiskProfileService.queryByPage(new HighRiskProfile(), PageRequest.of(page, size));
        List<HighRiskApplicationVo> vos = pr.getContent().stream().map(this::toVo).collect(Collectors.toList());
        Map<String,Object> resp = new HashMap<>();
        resp.put("content", vos);
        resp.put("totalElements", pr.getTotalElements());
        resp.put("totalPages", pr.getTotalPages());
        resp.put("page", page);
        return ResponseEntity.ok(resp);
    }

    /**
     * 获取详情
     */
    @GetMapping("/detail")
    public ResponseEntity<HighRiskApplicationVo> get(@RequestParam Long id) {
        HighRiskProfile profile = highRiskProfileService.queryById(id);
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toVo(profile));
    }

    /**
     * 更新高风险档案
     */
    @PutMapping("/update")
    public ResponseEntity<HighRiskApplicationVo> update(
            @RequestParam Long id,
            @RequestBody HighRiskApplicationRequest req) {
        HighRiskProfile profile = new HighRiskProfile();
        profile.setId(id);
        profile.setFarmerId(req.getFarmerId().longValue());
        profile.setRiskCauseDescription(req.getRiskCauseDescription());
        profile.setRiskTrendDescription(req.getRiskTrendDescription());
        if (req.getMonitoringStatus() != null) {
            profile.setMonitoringStatus(Integer.parseInt(req.getMonitoringStatus()));
        }
        profile.setRiskIdentifiedDate(req.getRiskIdentifiedDate());
        profile.setLastWarningTime(req.getLastWarningTime());
        profile.setTotalWarningCount(req.getTotalWarningCount());
        HighRiskProfile updated = highRiskProfileService.update(profile);
        return ResponseEntity.ok(toVo(updated));
    }

    /**
     * 删除高风险档案
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        highRiskProfileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private HighRiskApplicationVo toVo(HighRiskProfile p) {
        HighRiskApplicationVo vo = new HighRiskApplicationVo();
        vo.setFarmerId(p.getFarmerId().intValue());
        vo.setRiskCauseDescription(p.getRiskCauseDescription());
        vo.setRiskTrendDescription(p.getRiskTrendDescription());
        vo.setMonitoringStatus(p.getMonitoringStatus()!=null?p.getMonitoringStatus().toString():null);
        vo.setRiskIdentifiedDate(p.getRiskIdentifiedDate());
        vo.setLastWarningTime(p.getLastWarningTime());
        vo.setTotalWarningCount(p.getTotalWarningCount());
        vo.setCreateTime(p.getCreateTime());
        vo.setUpdateTime(p.getUpdateTime());
        vo.setStatus(p.getStatus());
        vo.setId(p.getId());
        return vo;
    }
}
