package com.ysu.drffpjcxt.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysu.drffpjcxt.entity.SupportMeasure;
import com.ysu.drffpjcxt.entity.SupportPlan;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.dto.support.SupportMeasureDTO;
import com.ysu.drffpjcxt.entity.dto.support.SupportPlanSaveRequest;
import com.ysu.drffpjcxt.mapper.SupportMeasureMapper;
import com.ysu.drffpjcxt.mapper.SupportPlanMapper;
import com.ysu.drffpjcxt.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 帮扶计划控制器测试类（包含完整的CRUD功能测试）
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 确保每个测试后数据回滚
public class SupportPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SupportPlanMapper supportPlanMapper;

    @Autowired
    private SupportMeasureMapper supportMeasureMapper;

    private User testPlanner;
    private Long existingPlanId; // 用于存储预创建的计划ID

    @BeforeEach
    void setUp() {
        // 准备一个测试用户
        testPlanner = new User();
        testPlanner.setRealName("测试帮扶干部");
        testPlanner.setPhone("13800009999");
        testPlanner.setPassword("-");
        testPlanner.setCreateTime(new Date());
        testPlanner.setUpdateTime(new Date());
        testPlanner.setIsDeleted(false);
        testPlanner.setEmployeeId("EMP123456");
        testPlanner.setIdCard("123456789012345678");
        userMapper.insert(testPlanner);

        // 准备一个已存在的帮扶计划和措施，用于更新、查询和删除测试
        SupportPlan plan = new SupportPlan();
        plan.setFarmerId(1L);
        plan.setPlanName("初始帮扶计划");
        plan.setId(testPlanner.getId());
        plan.setStatus("待审批");
        plan.setIsDeleted(false);
        plan.setCreateTime(new Date());
        plan.setUpdateTime(new Date());
        supportPlanMapper.insert(plan);
        existingPlanId = plan.getId(); // 保存ID

        SupportMeasure measure1 = new SupportMeasure();
        measure1.setPlanId(existingPlanId);
        measure1.setMeasureContent("初始措施1：提供种子");
        measure1.setIsDeleted(false);
        supportMeasureMapper.insert(measure1);

        SupportMeasure measure2 = new SupportMeasure();
        measure2.setPlanId(existingPlanId);
        measure2.setMeasureContent("初始措施2：技术培训");
        measure2.setIsDeleted(false);
        supportMeasureMapper.insert(measure2);
    }

    // ... testCreateSupportPlan_Success 和 testCreateSupportPlan_Fail_MissingParameters 测试用例保持不变 ...

    @Test
    @DisplayName("1. 成功创建帮扶计划")
    @WithMockUser(username = "13800009999")
    void testCreateSupportPlan_Success() throws Exception {
        // (此测试用例代码与上一版本相同，此处为保持完整性)
        SupportPlanSaveRequest request = new SupportPlanSaveRequest();
        request.setFarmerId(2L);
        request.setPlanName("新建的帮扶计划");
        request.setMeasures(Arrays.asList(new SupportMeasureDTO()));
        mockMvc.perform(post("/api/support-plans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").exists());
    }

    @Test
    @DisplayName("2. 成功查询帮扶计划详情")
    @WithMockUser(username = "13800009999")
    void testGetPlanDetail_Success() throws Exception {
        // 执行(Act): 发起GET请求
        mockMvc.perform(get("/api/support-plans/{id}", existingPlanId))
                // 断言(Assert): 验证响应结果
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planInfo.id").value(existingPlanId))
                .andExpect(jsonPath("$.planInfo.planName").value("初始帮扶计划"))
                .andExpect(jsonPath("$.measures").isArray())
                .andExpect(jsonPath("$.measures.length()").value(2))
                .andExpect(jsonPath("$.measures[0].measureContent").value("初始措施1：提供种子"));
    }

    @Test
    @DisplayName("3. 成功更新帮扶计划")
    @WithMockUser(username = "13800009999")
    void testUpdateSupportPlan_Success() throws Exception {
        // 准备(Arrange): 创建一个更新请求，措施列表变为一条
        SupportPlanSaveRequest updateRequest = new SupportPlanSaveRequest();
        updateRequest.setPlanName("更新后的帮扶计划名称");
        updateRequest.setMainGoal("这是更新后的主要目标");
        updateRequest.setFarmerId(1L);

        SupportMeasureDTO updatedMeasure = new SupportMeasureDTO();
        updatedMeasure.setMeasureType("更新后的类型");
        updatedMeasure.setMeasureContent("这是更新后的唯一措施");
        updatedMeasure.setBudgetAmount(new BigDecimal("1000.00"));
        updateRequest.setMeasures(Arrays.asList(updatedMeasure));

        // 执行(Act): 发起PUT请求
        mockMvc.perform(put("/api/support-plans/{id}", existingPlanId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                // 断言(Assert)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").value(existingPlanId));

        // 数据库验证
        SupportPlan updatedPlanInDb = supportPlanMapper.queryById(existingPlanId);
        Assertions.assertEquals("更新后的帮扶计划名称", updatedPlanInDb.getPlanName());

        List<SupportMeasure> measuresInDb = supportMeasureMapper.queryByPlanId(existingPlanId);
        Assertions.assertEquals(1, measuresInDb.size(), "措施数量应该从2变为1");
        Assertions.assertEquals("这是更新后的唯一措施", measuresInDb.get(0).getMeasureContent(), "措施内容应该被更新");
    }

    @Test
    @DisplayName("4. 成功删除帮扶计划")
    @WithMockUser(username = "13800009999")
    void testDeleteSupportPlan_Success() throws Exception {
        // 执行(Act): 发起DELETE请求
        mockMvc.perform(delete("/api/support-plans/{id}", existingPlanId))
                // 断言(Assert)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("删除成功"));

        // 数据库验证
        SupportPlan deletedPlanInDb = supportPlanMapper.queryById(existingPlanId);
        Assertions.assertTrue(deletedPlanInDb.getIsDeleted(), "计划的is_deleted字段应为true");

        // 因为queryByPlanId会过滤掉is_deleted=1的记录，所以这里应该查不到任何措施
        List<SupportMeasure> measuresInDb = supportMeasureMapper.queryByPlanId(existingPlanId);
        Assertions.assertEquals(0, measuresInDb.size(), "关联的措施也应该被逻辑删除，所以查询结果应为空");
    }
}