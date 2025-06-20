package com.ysu.drffpjcxt.controller.farmer;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysu.drffpjcxt.entity.vo.farmer.FarmerProfileVO;
import com.ysu.drffpjcxt.service.FarmerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sheng
 * @since 2025-06-20
 */
@RestController
@RequestMapping("/farmer/profile")
public class FarmerProfileController {

    @Autowired
    private FarmerProfileService farmerProfileService;

    /**
     * 分页查询所有农户信息（姓名、基本信息、风险状态）
     * @param pageNo 当前页码
     * @param pageSize 每页显示条数
     * @return 分页后的农户信息
     * 权限：禁止ROLE_USER角色的用户访问
     */
    @PreAuthorize("!hasRole('USER')")
    @GetMapping("/list/all")
    public ResponseEntity<?> getAllFarmers(
            @RequestParam(defaultValue = "1") long pageNo,
            @RequestParam(defaultValue = "10") long pageSize) {
        
        // 1. 创建Page对象
        Page<FarmerProfileVO> page = new Page<>(pageNo, pageSize);
        
        // 2. 调用Service层进行分页查询
        IPage<FarmerProfileVO> farmerPage = farmerProfileService.getAllFarmerWithRiskStatus(page);
        
        // 3. 返回成功结果
        return ResponseEntity.ok(farmerPage);
    }

}