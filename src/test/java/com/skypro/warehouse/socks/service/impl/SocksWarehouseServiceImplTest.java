package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.exception.BadRequestException;
import com.skypro.warehouse.socks.exception.SocksNotFoundException;
import com.skypro.warehouse.socks.mapper.SocksMapper;
import com.skypro.warehouse.socks.model.Operation;
import com.skypro.warehouse.socks.repository.SocksWarehouseRepository;
import com.skypro.warehouse.socks.service.ValidatorSocksRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Тесты для методов {@link SocksWarehouseServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class SocksWarehouseServiceImplTest {

    @InjectMocks
    SocksWarehouseServiceImpl out;

    @Mock
    SocksWarehouseRepository socksWarehouseRepository;
    @Spy
    SocksMapper socksMapper;

    @Spy
    ValidatorSocksRequest validatorSocksRequest;

    @Test
    void incomeSocksNewPair() {
        SocksDto socksDto = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(10)
                .build();
        SocksEntity socksEntity = socksMapper.dtoToEntity(socksDto);
        socksEntity.setId(1);
        when(socksWarehouseRepository.findByColorAndAndCottonPart("red", 10)).thenReturn(Optional.empty());
        when(socksWarehouseRepository.save(socksEntity)).thenReturn(socksEntity);
        out.incomeSocks(socksDto);
        verify(socksWarehouseRepository, times(1)).save(socksEntity);
    }

    @Test
    void incomeSockAddQuantity() {
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
        SocksEntity socksEntity2 = SocksEntity.builder()
                .id(1)
                .color("red")
                .cottonPart(10)
                .quantity(30)
                .build();
        when(socksWarehouseRepository.findByColorAndAndCottonPart("red", 10)).thenReturn(Optional.of(socksEntity));
        when(socksWarehouseRepository.save(socksEntity2)).thenReturn(socksEntity2);
        out.incomeSocks(socksDto);
        verify(socksWarehouseRepository, times(1)).save(socksEntity2);
    }

    @Test
    void outcomeSocks() {
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
        out.outcomeSocks(socksDto);
        verify(socksWarehouseRepository, times(1)).save(socksEntity2);
    }

    @Test
    void outcomeSocksNotFoundException() {
        SocksDto socksDto = SocksDto.builder()
                .color("red")
                .cottonPart(10)
                .quantity(2)
                .build();
        when(socksWarehouseRepository.findByColorAndAndCottonPart(any(), any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> out.outcomeSocks(socksDto)).isInstanceOf(SocksNotFoundException.class);
    }

    @Test
    void getSocksLessCotton() {
        when(socksWarehouseRepository.getQuantityByColorAndLessCotton("red", 50)).thenReturn(10);
        String expected = "10";
        String actual = out.getSocks("red", Operation.lessThan, 50);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getSocksEqualCotton() {
        when(socksWarehouseRepository.getQuantityByColorAndEqualsCotton("red", 50)).thenReturn(10);
        String expected = "10";
        String actual = out.getSocks("red", Operation.equal, 50);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getSocksMoreCotton() {
        when(socksWarehouseRepository.getQuantityByColorAndMoreCotton("red", 50)).thenReturn(10);
        String expected = "10";
        String actual = out.getSocks("red", Operation.moreThan, 50);
        assertThat(actual).isEqualTo(expected);
    }

}