package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.exception.BadRequestException;
import com.skypro.warehouse.socks.service.ValidatorRequest;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class ValidatorRequestImpl implements ValidatorRequest {

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

    @Override
    public void validateSocksRequest(String color, Integer cottonPart) {
        if (color.isEmpty() || cottonPart < 0 || cottonPart > 100) {
            log.error("Incorrect request for working");
            throw new BadRequestException();
        }
    }
}
