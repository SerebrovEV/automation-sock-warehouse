package com.skypro.warehouse.socks.service;

import com.skypro.warehouse.socks.dto.SocksDto;

public interface SocksWarehouseService {
    void incomeSocks(SocksDto socksDto);

    void outcomeSocks();

    int getSocks();
}
