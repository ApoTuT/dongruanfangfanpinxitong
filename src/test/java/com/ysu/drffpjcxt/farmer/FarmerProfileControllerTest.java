package com.ysu.drffpjcxt.farmer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysu.drffpjcxt.entity.vo.farmer.FarmerProfileVO;
import com.ysu.drffpjcxt.service.FarmerProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * FarmerProfileController的测试类
 *
 * @author sheng
 * @since 2025-06-20
 */
@SpringBootTest
@AutoConfigureMockMvc
public class FarmerProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FarmerProfileService farmerProfileService;

    @Autowired
    private ObjectMapper objectMapper;

    private IPage<FarmerProfileVO> mockPage;

    @BeforeEach
    void setUp() {
        // 准备一个模拟的农户信息VO
        FarmerProfileVO farmer = new FarmerProfileVO();

        // 将模拟数据放入一个List
        List<FarmerProfileVO> farmerList = Collections.singletonList(farmer);

        // 创建一个模拟的Page对象
        mockPage = new Page<>(1, 10);
        mockPage.setRecords(farmerList);
        mockPage.setTotal(1);
    }

    @Test
    @WithMockUser(roles = "TOWNSHIP_CADRE") // 模拟一个ADMIN角色的用户，他不是ROLE_USER，应该可以访问
    void testGetAllFarmers_Success_ForAdmin() throws Exception {
        // 当service层被调用时，返回我们准备好的模拟Page对象
        when(farmerProfileService.getAllFarmerWithRiskStatus(any(Page.class))).thenReturn(mockPage);

        // 执行GET请求
        mockMvc.perform(get("/farmer/profile/list/all")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                // 验证HTTP状态码是否为200 (OK)
                .andExpect(status().isOk())
                // 验证返回的JSON结构和数据是否符合预期
                .andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.records[0].name").value("测试农户"))
                .andExpect(jsonPath("$.records[0].riskStatus").value("无风险"));
    }

    @Test
    @WithMockUser(roles = "USER") // 模拟一个USER角色的用户，他应该被禁止访问
    void testGetAllFarmers_Forbidden_ForUser() throws Exception {
        // 执行GET请求
        mockMvc.perform(get("/farmer/profile/list/all")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                // 验证HTTP状态码是否为403 (Forbidden)
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAllFarmers_Unauthorized_ForGuest() throws Exception {
        // 不带任何认证信息，模拟游客访问
        mockMvc.perform(get("/farmer/profile/list/all")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                // 验证HTTP状态码是否为401 (Unauthorized) 或 403 (Forbidden)，取决于您的具体安全配置
                // 在您的项目中，未登录访问受保护资源通常返回403
                .andExpect(status().isForbidden());
    }
}