package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.exception.BadRequestException;
import com.skypro.warehouse.socks.service.ValidatorSocksRequest;
import com.skypro.warehouse.socks.controller.SocksWarehouseController;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * Валидация входящих запросов на {@link SocksWarehouseController}
 */
@Service
@Log4j
public class ValidatorSocksRequestImpl implements ValidatorSocksRequest {

    /**
     * Валидация запроса с {@link SocksDto}
     * @param socksDto
     */
    @Override
    public void validateSocksRequest(SocksDto socksDto) {
        if (socksDto.getColor().isEmpty()
                || socksDto.getCottonPart() < 0
                || socksDto.getCottonPart() > 100
                || socksDto.getQuantity() < 0) {
            log.error("Incorrect request for working");
            throw new BadRequestException();
        }
    }

    /**
     * Валидация запроса c параметрами
     * @param color цвет;
     * @param cottonPart процент хлопка в составе
     */
    @Override
    public void validateSocksRequest(String color, Integer cottonPart) {
        if (color.isEmpty() || cottonPart < 0 || cottonPart > 100) {
            log.error("Incorrect request for working");
            throw new BadRequestException();
        }
    }
}
