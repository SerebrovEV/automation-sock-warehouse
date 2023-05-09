package com.skypro.warehouse.socks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.mapper.SocksMapper;
import com.skypro.warehouse.socks.repository.SocksWarehouseRepository;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import com.skypro.warehouse.socks.service.ValidatorSocksRequest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SocksWarehouseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SocksWarehouseRepository socksWarehouseRepository;

    @SpyBean
    SocksWarehouseService socksWarehouseService;

    @SpyBean
    SocksMapper socksMapper;

    @SpyBean
    ValidatorSocksRequest validatorSocksRequest;

    @Test
    void incomeSocksNewColor() throws Exception {

        SocksDto socksDto = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(20)
                .build();

        SocksEntity socksEntity = SocksEntity.builder()
                .id(1)
                .color("red")
                .cottonPart(10)
                .quantity(20)
                .build();

        when(socksWarehouseRepository.findByColorAndAndCottonPart("red", 10)).thenReturn(Optional.empty());
        when(socksWarehouseRepository.save(socksEntity)).thenReturn(socksEntity);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/income")
                                .content(objectMapper.writeValueAsString(socksDto))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());

    }

    @Test
    void incomeSocksAddQuantitySocks() throws Exception {
        SocksDto socksDto = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(10)
                .build();
        SocksEntity socksEntity = SocksEntity.builder()
                .id(1)
                .color("red")
                .cottonPart(10)
                .quantity(20)
                .build();
        SocksEntity socksResult = SocksEntity.builder()
                .id(1)
                .color("red")
                .cottonPart(10)
                .quantity(30)
                .build();

        when(socksWarehouseRepository.findByColorAndAndCottonPart("red", 10)).thenReturn(Optional.of(socksEntity));
        when(socksWarehouseRepository.save(socksResult)).thenReturn(socksResult);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/income")
                                .content(objectMapper.writeValueAsString(socksDto))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());

    }

    @Test
    void incomeSocksBadRequestException() throws Exception {
        SocksDto incorrectCotton = SocksDto.builder()
                .color("red")
                .cottonPart(-10)
                .quantity(10)
                .build();

        SocksDto incorrectQuantity = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(-10)
                .build();

        SocksDto NullColor = SocksDto.builder()
                .cottonPart(10)
                .quantity(-10)
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/income")
                                .content(objectMapper.writeValueAsString(incorrectCotton))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/income")
                                .content(objectMapper.writeValueAsString(incorrectQuantity))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/income")
                                .content(objectMapper.writeValueAsString(NullColor))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

    }

    @Test
    void outcomeSocks() throws Exception {
        SocksDto socksDto = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(2)
                .build();
        SocksEntity socksEntity = SocksEntity.builder()
                .id(1)
                .color("red")
                .cottonPart(10)
                .quantity(20)
                .build();
        SocksEntity socksEntity2 = SocksEntity.builder()
                .id(1)
                .color("red")
                .cottonPart(10)
                .quantity(18)
                .build();

        when(socksWarehouseRepository.findByColorAndAndCottonPart("red", 10)).thenReturn(Optional.of(socksEntity));
        when(socksWarehouseRepository.save(socksEntity2)).thenReturn(socksEntity2);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/outcome")
                                .content(objectMapper.writeValueAsString(socksDto))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    void outcomeSocksNotFoundException() throws Exception {
        SocksDto socksDto = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(2)
                .build();

        when(socksWarehouseRepository.findByColorAndAndCottonPart("red", 10)).thenReturn(Optional.empty());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/outcome")
                                .content(objectMapper.writeValueAsString(socksDto))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());
    }

    @Test
    void outcomeSocksIncorrectRequest() throws Exception {
        SocksDto incorrectCotton = SocksDto.builder()
                .color("red")
                .cottonPart(-10)
                .quantity(10)
                .build();

        SocksDto incorrectQuantity = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(-10)
                .build();

        SocksDto NullColor = SocksDto.builder()
                .cottonPart(10)
                .quantity(-10)
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/outcome")
                                .content(objectMapper.writeValueAsString(incorrectCotton))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/outcome")
                                .content(objectMapper.writeValueAsString(incorrectQuantity))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/socks/outcome")
                                .content(objectMapper.writeValueAsString(NullColor))
                                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

    }


    @Test
    void getSocks() throws Exception {
        String color = "red";
        String operationLess = "lessThan";
        String operationEqual = "equal";
        String operationMore = "moreThan";

        Integer cottonPart = 10;

        String expected = "10";
        String expected2 = "20";
        String expected3 = "30";

        when(socksWarehouseRepository.getQuantityByColorAndLessCotton(color, cottonPart)).thenReturn(10);
        when(socksWarehouseRepository.getQuantityByColorAndEqualsCotton(color, cottonPart)).thenReturn(20);
        when(socksWarehouseRepository.getQuantityByColorAndMoreCotton(color, cottonPart)).thenReturn(30);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/socks?color=" + color + "&operation=" + operationLess + "&cottonPart=" + cottonPart))

                .andExpect(status().isOk())
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    String actual = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8),String.class);
                    assertThat(actual).isEqualTo(expected);
                });

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/socks?color=" + color + "&operation=" + operationEqual + "&cottonPart=" + cottonPart))

                .andExpect(status().isOk())
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    String actual =objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8),String.class);
                    assertThat(actual).isEqualTo(expected2);
                });

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/socks?color=" + color + "&operation=" + operationMore + "&cottonPart=" + cottonPart))

                .andExpect(status().isOk())
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    String actual =objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8),String.class);
                    assertThat(actual).isEqualTo(expected3);
                });

    }
}