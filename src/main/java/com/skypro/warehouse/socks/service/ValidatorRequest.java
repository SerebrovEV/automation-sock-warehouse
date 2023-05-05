package com.skypro.warehouse.socks.service;

import com.skypro.warehouse.socks.dto.SocksDto;

public interface ValidatorRequest {

    void validateSocksRequest(SocksDto socksDto);
    void validateSocksRequest(String color, Integer cottonPart);

}
