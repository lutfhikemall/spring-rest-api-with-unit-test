package com.test.indivara.test_indivara;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.indivara.test_indivara.dto.request.CreateUserRequest;
import com.test.indivara.test_indivara.dto.request.LoginUserRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateUser() throws Exception {
        // Prepare
        CreateUserRequest user = new CreateUserRequest();

        user.setFullName("Test User");
        user.setEmail("test@user.com");
        user.setPassword("12345678");

        // Arrange
        ResultActions result = mockMvc.perform(post("/api/user/register").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        // Assert
        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    void shouldLogineUser() throws Exception {
        // Prepare
        LoginUserRequest user = new LoginUserRequest();

        user.setEmail("test@user.com");
        user.setPassword("12345678");

        // Arrange
        ResultActions result = mockMvc.perform(post("/api/user/login").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        // Assert
        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK")).andExpect(jsonPath("$.data").isNotEmpty());
    }
}
