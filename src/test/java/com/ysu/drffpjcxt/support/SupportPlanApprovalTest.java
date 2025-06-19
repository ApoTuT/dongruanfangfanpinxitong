package com.ysu.drffpjcxt.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysu.drffpjcxt.entity.ApprovalRecord;
import com.ysu.drffpjcxt.entity.Role;
import com.ysu.drffpjcxt.entity.SupportPlan;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.UserRole;
import com.ysu.drffpjcxt.entity.dto.support.ApprovalRequest;
import com.ysu.drffpjcxt.mapper.ApprovalRecordMapper;
import com.ysu.drffpjcxt.mapper.RoleMapper;
import com.ysu.drffpjcxt.mapper.SupportPlanMapper;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.mapper.UserRoleMapper;
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

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("帮扶计划审批功能测试")
public class SupportPlanApprovalTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private SupportPlanMapper supportPlanMapper;
    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;

    private User countyCadre; // 县级干部 (审批者)
    private User townshipCadre; // 乡级干部 (计划制定者)
    private Long planIdToApprove; // 待审批的计划ID

    @BeforeEach
    void setUp() {
        // 1. 创建角色
        Role countyRole = Role.builder()
                .roleName("县级帮扶干部")
                .roleCode("ROLE_COUNTY_CADRE")
                .description("负责审批县级帮扶计划")
                .build();
        roleMapper.insert(countyRole);

        Role townshipRole = Role.builder()
                .roleName("乡级帮扶干部")
                .roleCode("ROLE_TOWNSHIP_CADRE")
                .description("负责制定乡级帮扶计划")
                .build();
        roleMapper.insert(townshipRole);

        // 2. 创建用户 - 确保设置所有必要的字段
        countyCadre = User.builder()
                .employeeId("COUNTY001")
                .provinceCode("110000")
                .cityCode("110100")
                .realName("县级干部-王审批")
                .phone("13800138000") // 用于模拟登录
                .password("123456")
                .idCard("110101199001010001")
                .email("123456")
                .isDeleted(false) // 明确设置为未删除
                .status(1) // 设置为正常状态
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userMapper.insert(countyCadre);

        townshipCadre = User.builder()
                .employeeId("TS001")
                .provinceCode("110000")
                .cityCode("110100")
                .countyCode("110101")
                .realName("乡级干部-张制定")
                .phone("13700137000")
                .password("123456")
                .idCard("110101199002020002")
                .email("123456")
                .isDeleted(false) // 明确设置为未删除
                .status(1) // 设置为正常状态
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userMapper.insert(townshipCadre);
        
        // 3. 关联用户和角色
        userRoleMapper.insert(UserRole.builder()
                .userId(countyCadre.getId())
                .roleId(countyRole.getId())
                .build());
        userRoleMapper.insert(UserRole.builder()
                .userId(townshipCadre.getId())
                .roleId(townshipRole.getId())
                .build());

        // 4. 创建一个待审批的计划
        SupportPlan plan = new SupportPlan();
        plan.setFarmerId(1L);
        plan.setPlanName("测试待审批计划");
        plan.setCreatedBy(townshipCadre.getId()); // 由乡级干部制定
        plan.setStatus("待审批"); // 核心状态
        plan.setIsDeleted(false);
        supportPlanMapper.insert(plan);
        planIdToApprove = plan.getId();
    }

    @Test
    @DisplayName("成功场景 - 县级干部批准计划")
    @WithMockUser(username = "13800138000", roles = "COUNTY_CADRE") // 模拟县级干部登录
    void testApprovePlan_AsCountyCadre_ShouldSucceed() throws Exception {
        // 准备(Arrange)
        ApprovalRequest request = new ApprovalRequest();
        request.setApproved(true);
        request.setComments("情况属实，批准执行。");

        // 执行(Act) & 断言(Assert)
        mockMvc.perform(post("/api/support-plans/{id}/approval", planIdToApprove)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("审批操作成功"));
        
        // 数据库验证
        SupportPlan planInDb = supportPlanMapper.queryById(planIdToApprove);
        Assertions.assertEquals("已批准", planInDb.getStatus(), "计划状态应变为'已批准'");

        List<ApprovalRecord> records = approvalRecordMapper.findByEntity("SUPPORT_PLAN", planIdToApprove);
        Assertions.assertEquals(1, records.size(), "应该生成一条审批记录");
        Assertions.assertEquals(countyCadre.getId(), records.get(0).getApproverId(), "审批人ID应正确");
        Assertions.assertEquals("情况属实，批准执行。", records.get(0).getComments(), "审批意见应正确记录");
    }

    @Test
    @DisplayName("成功场景 - 县级干部驳回计划")
    @WithMockUser(username = "13800138000", roles = "COUNTY_CADRE") // 模拟县级干部登录
    void testRejectPlan_AsCountyCadre_ShouldSucceed() throws Exception {
        ApprovalRequest request = new ApprovalRequest();
        request.setApproved(false);
        request.setComments("帮扶措施不具体，请修改后重新提交。");

        mockMvc.perform(post("/api/support-plans/{id}/approval", planIdToApprove)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        SupportPlan planInDb = supportPlanMapper.queryById(planIdToApprove);
        Assertions.assertEquals("已驳回", planInDb.getStatus(), "计划状态应变为'已驳回'");
    }

    @Test
    @DisplayName("权限失败场景 - 乡级干部尝试审批")
    @WithMockUser(username = "13700137000", roles = "TOWNSHIP_CADRE") // 模拟乡级干部登录
    void testApprovePlan_AsTownshipCadre_ShouldBeForbidden() throws Exception {
        ApprovalRequest request = new ApprovalRequest();
        request.setApproved(true);
        request.setComments("自己批准自己");

        mockMvc.perform(post("/api/support-plans/{id}/approval", planIdToApprove)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden()); // 期望403 Forbidden
    }

    @Test
    @DisplayName("业务失败场景 - 审批一个已完成的计划")
    @WithMockUser(username = "13800138000", roles = "COUNTY_CADRE") // 模拟县级干部登录
    void testApprovePlan_WhenStatusIsNotPending_ShouldFail() throws Exception {
        // 先手动把计划状态改成“已批准”
        SupportPlan plan = supportPlanMapper.queryById(planIdToApprove);
        plan.setStatus("已批准");
        supportPlanMapper.update(plan);

        ApprovalRequest request = new ApprovalRequest();
        request.setApproved(true);
        request.setComments("重复审批");

        mockMvc.perform(post("/api/support-plans/{id}/approval", planIdToApprove)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest()) // 期望400 Bad Request
                .andExpect(result -> Assertions.assertEquals("该计划不是待审批状态，无法操作", result.getResponse().getContentAsString()));
    }
}

