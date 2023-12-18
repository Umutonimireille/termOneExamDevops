package com.devops.v1.examTermOne.controllers;

import com.devops.v1.examTermOne.dtos.DoMathRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;



@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testDoMath_ValidRequest() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 4.0, "+");

        mockMvc.perform(post("/api/math/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calcResponse", is(7.0)));
    }

    @Test
    public void testDoMath_DivisionByZero() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(5.0, 0.0, "/");

        mockMvc.perform(post("/api/math/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(asJsonString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDoMath_UnknownOperation() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 4.0, "unknown");

        mockMvc.perform(post("/api/math/doMath")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isBadRequest());
    }

    // Add more tests for other scenarios, invalid inputs, and edge cases

    private String asJsonString(Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }



}
