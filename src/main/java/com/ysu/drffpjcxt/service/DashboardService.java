package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.Dashboard;
import com.ysu.drffpjcxt.entity.vo.data.DashboardVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 仪表盘表(Dashboard)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:18
 */
public interface DashboardService
{
    DashboardVO getDashboardStatus();
}
