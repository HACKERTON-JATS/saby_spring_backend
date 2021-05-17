package com.jats.savy.savy.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jats.savy.savy.entity.admin.Admin;
import com.jats.savy.savy.entity.admin.AdminRepository;
import com.jats.savy.savy.payload.response.AuthResponse;
import com.jats.savy.savy.security.JwtTokenProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        adminRepository.save(new Admin("code"));
    }

    @AfterEach
    void cleanUp() {
        adminRepository.deleteAll();
    }

    @Test
    public void createTokenSuccessfully() throws Exception {
        MvcResult result = mvc.perform(post("/admin/auth")
                .content(new ObjectMapper().writeValueAsString("code"))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        String accessToken = new ObjectMapper().readValue(result.getResponse().getContentAsString(), AuthResponse.class).getAccessToken();

        Assertions.assertTrue(jwtTokenProvider.validateToken(accessToken));
    }

    @Test
    public void createTokenFailure() throws Exception {
        mvc.perform(post("/admin/auth")
                .content(new ObjectMapper().writeValueAsString("code11"))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}
