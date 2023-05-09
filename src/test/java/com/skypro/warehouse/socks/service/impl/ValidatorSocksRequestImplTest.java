package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

/**
 * Тесты для методов {@link ValidatorSocksRequestImpl}
 */
class ValidatorSocksRequestImplTest {
    private final ValidatorSocksRequestImpl out = new ValidatorSocksRequestImpl();

    @Test
    void validateSocksRequest() {
        SocksDto socksDto1 = SocksDto.builder()
                .color(null)
                .cottonPart(10)
                .quantity(2).build();

        SocksDto socksDto2 = SocksDto.builder()
                .color("red")
                .cottonPart(101)
                .quantity(2).build();

        SocksDto socksDto3 =
                SocksDto.builder()
                        .color("red")
                        .cottonPart(-10)
                        .quantity(20).build();

        SocksDto socksDto4 =
                SocksDto.builder()
                        .color("red")
                        .cottonPart(10)
                        .quantity(-2).build();

        assertThatThrownBy(() -> out.validateSocksRequest(socksDto1))
                .isInstanceOf(BadRequestException.class);
        assertThatThrownBy(() -> out.validateSocksRequest(socksDto2))
                .isInstanceOf(BadRequestException.class);
        assertThatThrownBy(() -> out.validateSocksRequest(socksDto3))
                .isInstanceOf(BadRequestException.class);
        assertThatThrownBy(() -> out.validateSocksRequest(socksDto4))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void testValidateSocksRequest() {
        assertThatThrownBy(() -> out.validateSocksRequest("", 10))
                .isInstanceOf(BadRequestException.class);
        assertThatThrownBy(() -> out.validateSocksRequest("red", -10))
                .isInstanceOf(BadRequestException.class);
        assertThatThrownBy(() -> out.validateSocksRequest("red", 101))
                .isInstanceOf(BadRequestException.class);
    }
}