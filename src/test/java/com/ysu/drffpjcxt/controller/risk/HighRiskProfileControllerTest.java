package com.ysu.drffpjcxt.controller.risk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysu.drffpjcxt.common.JwtUtil;
import com.ysu.drffpjcxt.config.SecurityConfig;
import com.ysu.drffpjcxt.entity.HighRiskProfile;
import com.ysu.drffpjcxt.entity.dto.risk.HighRiskApplicationRequest;
import com.ysu.drffpjcxt.service.HighRiskProfileService;
import com.ysu.drffpjcxt.service.impl.auth.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Regenerated Test for HighRiskProfileController.
 *
 * This test class is specifically designed to test the endpoints of HighRiskProfileController.
 *
 * Key Features:
 * 1.  @WebMvcTest: Creates a focused test environment for the controller, without loading the entire application.
 * 2.  @Import(SecurityConfig.class, JwtUtil.class): Manually imports the necessary security configuration
 * and supporting beans, which are not loaded by default in @WebMvcTest. This is critical for testing
 * secured endpoints.
 * 3.  @MockBean: Creates mock versions of the service dependencies (HighRiskProfileService and UserDetailsServiceImpl)
 * to isolate the controller's logic.
 * 4.  @WithMockUser(roles = "ADMIN"): Simulates a request from an authenticated user with the 'ADMIN' role. This is necessary
 * to satisfy the controller's security requirement: @PreAuthorize("!hasRole('USER')").
 * 5.  Tests for all Endpoints: Includes specific tests for create, select (list), detail, update, and delete operations,
 * matching the controller's API.
 */
@WebMvcTest(HighRiskProfileController.class)
@Import({SecurityConfig.class, JwtUtil.class})
public class HighRiskProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HighRiskProfileService highRiskProfileService;

    // Mock UserDetailsService as it's a dependency for SecurityConfig
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    private HighRiskProfile sampleProfile;
    private HighRiskApplicationRequest sampleRequest;

    @BeforeEach
    void setUp() {
        // Common setup for tests
        sampleProfile = new HighRiskProfile();
        sampleProfile.setId(1L);
        sampleProfile.setFarmerId(100L);
        sampleProfile.setRiskCauseDescription("Test Risk Cause");
        sampleProfile.setRiskTrendDescription("Test Risk Trend");
        sampleProfile.setMonitoringStatus(1);
        sampleProfile.setCreateTime(new Date());
        sampleProfile.setUpdateTime(new Date());

        sampleRequest = new HighRiskApplicationRequest();
        sampleRequest.setFarmerId(100);
        sampleRequest.setRiskCauseDescription("Test Risk Cause");
        sampleRequest.setRiskTrendDescription("Test Risk Trend");
        sampleRequest.setMonitoringStatus("1");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateHighRiskProfile() throws Exception {
        when(highRiskProfileService.insert(any(HighRiskProfile.class))).thenReturn(sampleProfile);

        mockMvc.perform(post("/api/farmer/high-risk-profile/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.farmerId").value(100));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testListHighRiskProfiles() throws Exception {
        Page<HighRiskProfile> pageResult = new PageImpl<>(Collections.singletonList(sampleProfile), PageRequest.of(0, 10), 1);

        when(highRiskProfileService.queryByPage(any(), any())).thenReturn(pageResult);

        mockMvc.perform(get("/api/farmer/high-risk-profile/select")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].farmerId").value(100));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetHighRiskProfileDetail() throws Exception {
        when(highRiskProfileService.queryById(1L)).thenReturn(sampleProfile);

        mockMvc.perform(get("/api/farmer/high-risk-profile/detail")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.farmerId").value(100))
                .andExpect(jsonPath("$.riskCauseDescription").value("Test Risk Cause"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetHighRiskProfileDetail_NotFound() throws Exception {
        when(highRiskProfileService.queryById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/farmer/high-risk-profile/detail")
                        .param("id", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateHighRiskProfile() throws Exception {
        when(highRiskProfileService.update(any(HighRiskProfile.class))).thenReturn(sampleProfile);

        mockMvc.perform(put("/api/farmer/high-risk-profile/update")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.farmerId").value(100));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteHighRiskProfile() throws Exception {
        doNothing().when(highRiskProfileService).deleteById(1L);

        mockMvc.perform(delete("/api/farmer/high-risk-profile/delete")
                        .param("id", "1"))
                .andExpect(status().isNoContent());
    }
}