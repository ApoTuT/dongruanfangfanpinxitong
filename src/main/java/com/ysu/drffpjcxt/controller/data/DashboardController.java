package com.ysu.drffpjcxt.controller.data;

import com.ysu.drffpjcxt.entity.vo.data.DashboardVO;
import com.ysu.drffpjcxt.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sheng
 * @date 2025-06-24
 */
@RestController
@RequestMapping("/dashboard")
@PreAuthorize("!hasRole('USER')")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * 获取仪表盘统计数据
     *
     */
    @GetMapping("/status")
    public DashboardVO getDashboardStats() {
        return dashboardService.getDashboardStatus();
    }
}