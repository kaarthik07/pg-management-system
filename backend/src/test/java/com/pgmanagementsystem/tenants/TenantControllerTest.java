package com.pgmanagementsystem.tenants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgmanagementsystem.config.HealthController;
import com.pgmanagementsystem.tenants.dto.TenantRequest;
import com.pgmanagementsystem.tenants.service.TenantService;
import com.pgmanagementsystem.tenants.web.TenantController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TenantController.class, HealthController.class})
@Import({TenantService.class})
@AutoConfigureMockMvc
class TenantControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void tenantCrud_happyPath() throws Exception {
        // 1) Create
        TenantRequest req = new TenantRequest();
        req.setFullName("Test User");
        req.setPhone("+919876543210");
        req.setEmail("user@example.com");
        req.setStatus("ACTIVE");

        MvcResult create = mvc.perform(post("/api/v1/tenants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fullName").value("Test User"))
                .andReturn();

        String json = create.getResponse().getContentAsString();
        String id = mapper.readTree(json).get("id").asText();
        assertThat(id).isNotBlank();

        // 2) List (should include created tenant)
        mvc.perform(get("/api/v1/tenants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());

        // 3) Get by id
        mvc.perform(get("/api/v1/tenants/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        // 4) Update
        req.setFullName("Test User Updated");
        mvc.perform(put("/api/v1/tenants/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Test User Updated"));

        // 5) Delete
        mvc.perform(delete("/api/v1/tenants/{id}", id))
                .andExpect(status().isNoContent());

        // 6) Ensure not found after delete
        mvc.perform(get("/api/v1/tenants/{id}", id))
                .andExpect(status().isNotFound());
    }
}
