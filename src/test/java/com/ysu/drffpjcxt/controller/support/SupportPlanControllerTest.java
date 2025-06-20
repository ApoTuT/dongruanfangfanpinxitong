package com.ysu.drffpjcxt.controller.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysu.drffpjcxt.entity.dto.support.ApprovalRequest;
import com.ysu.drffpjcxt.entity.dto.support.SupportPlanSaveRequest;
import com.ysu.drffpjcxt.entity.vo.support.SupportPlanDetailVO;
import com.ysu.drffpjcxt.service.SupportPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupportPlanController.class)
public class SupportPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupportPlanService supportPlanService;

    @Autowired
    private ObjectMapper objectMapper;

    private SupportPlanSaveRequest supportPlanSaveRequest;
    private SupportPlanDetailVO supportPlanDetailVO;
    private ApprovalRequest approvalRequest;
    private Principal mockPrincipal;

    @BeforeEach
    void setUp() {
        supportPlanSaveRequest = new SupportPlanSaveRequest();
        // ... initialize supportPlanSaveRequest with test data

        supportPlanDetailVO = new SupportPlanDetailVO();
        // ... initialize supportPlanDetailVO with test data

        approvalRequest = new ApprovalRequest();
        // ... initialize approvalRequest with test data

        mockPrincipal = () -> "testUser";
    }

    @Test
    @WithMockUser(username="testUser")
    void createSupportPlan_shouldReturnOk() throws Exception {
        when(supportPlanService.createSupportPlan(any(SupportPlanSaveRequest.class), any(Principal.class))).thenReturn(1L);

        mockMvc.perform(post("/api/support-plans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supportPlanSaveRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").value(1L));
    }

    @Test
    void getPlanDetail_shouldReturnOk() throws Exception {
        when(supportPlanService.getAllPlanDetail()).thenReturn(Collections.singletonList(supportPlanDetailVO));

        mockMvc.perform(get("/api/support-plans/detail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    @WithMockUser(username="testUser")
    void updateSupportPlan_shouldReturnOk() throws Exception {
        when(supportPlanService.updateSupportPlan(eq(1L), any(SupportPlanSaveRequest.class), any(Principal.class))).thenReturn(1L);

        mockMvc.perform(put("/api/support-plans/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supportPlanSaveRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").value(1L));
    }

    @Test
    void deleteSupportPlan_shouldReturnOk() throws Exception {
        doNothing().when(supportPlanService).deleteSupportPlanById(1L);

        mockMvc.perform(delete("/api/support-plans/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("删除成功"));
    }

    @Test
    @WithMockUser(username="testUser", authorities = {"county_cadre"})
    void approvePlan_shouldReturnOk() throws Exception {
        doNothing().when(supportPlanService).approvePlan(eq(1L), any(ApprovalRequest.class), any(Principal.class));

        mockMvc.perform(post("/api/support-plans/approval/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(approvalRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("审批操作成功"));
    }
}

