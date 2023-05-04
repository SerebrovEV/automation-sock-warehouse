package com.skypro.warehouse.socks.service;

import com.skypro.warehouse.socks.dto.SocksDto;

public interface SocksWarehouseService {
    void incomeSocks(SocksDto socksDto);

    void outcomeSocks(SocksDto socksDto);

    int getSocks(String color, String operation, Integer cottonPart);
}
