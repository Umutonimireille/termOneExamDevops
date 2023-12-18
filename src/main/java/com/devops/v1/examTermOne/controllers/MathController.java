package com.devops.v1.examTermOne.controllers;

import com.devops.v1.examTermOne.dtos.DoMathRequestDto;
import com.devops.v1.examTermOne.exceptions.InvalidOperationException;
import com.devops.v1.examTermOne.payload.ApiResponse;
import com.devops.v1.examTermOne.serviceImpls.MathOperatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MathControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MathOperatorImpl mathOperatorImpl;

    @Test
    void testDoMathOperation_Success() throws Exception {
        // Create a DoMathRequestDto for the test
        DoMathRequestDto requestDto = new DoMathRequestDto(2, 5, "+");

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/do_math")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value(7.0));
    }

    // Utility method to convert objects to JSON format
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
