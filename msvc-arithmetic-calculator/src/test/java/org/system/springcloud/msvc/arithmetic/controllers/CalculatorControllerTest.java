package org.system.springcloud.msvc.arithmetic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.system.springcloud.msvc.arithmetic.entities.dtos.RecordDto;
import org.system.springcloud.msvc.arithmetic.entities.dtos.ValuesDto;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.system.springcloud.msvc.arithmetic.services.impl.OperationServiceImpl;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CalculatorController.class)
class CalculatorControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    OperationServiceImpl operationService;

    @Test
    void additionTest() throws Exception {
        ValuesDto valuesDto = ValuesDto.builder()
                .valueOne(1D)
                .valueTwo(2D)
                .build();

        given(operationService.calculate(valuesDto, 1L,1L)).willReturn(RecordDto.builder()
                        .id(1L)
                        .amount("3")
                        .userBalance(2)
                        .operationId(1L)
                        .date(LocalDateTime.now())
                        .operationResponse(true)
                .build());

        mockMvc.perform(post("/api/arithmetic/calculate")
                        .param("userId", "1")
                        .param("operationId", "1")
                .content(new ObjectMapper().writeValueAsString(valuesDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }


    @Test
    void additionTestThrowException() throws Exception {
        ValuesDto valuesDto = ValuesDto.builder()
                .valueOne(1D)
                .valueTwo(2D)
                .build();

        given(operationService.calculate(valuesDto, 1L,1L)).willThrow(new Exception());

        mockMvc.perform(post("/api/arithmetic/calculate")
                        .param("userId", "1")
                        .param("operationId", "1")
                        .content(new ObjectMapper().writeValueAsString(valuesDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }



}