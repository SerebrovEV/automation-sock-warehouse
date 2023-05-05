package com.skypro.warehouse.socks.service;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.model.Operation;

public interface SocksWarehouseService {
    void incomeSocks(SocksDto socksDto);

    void outcomeSocks(SocksDto socksDto);

    int getSocks(String color, Operation operation, Integer cottonPart);
}
