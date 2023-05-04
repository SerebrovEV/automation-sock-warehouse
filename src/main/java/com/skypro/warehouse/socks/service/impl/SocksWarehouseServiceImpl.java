package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocksWarehouseServiceImpl implements SocksWarehouseService {
    // private final SocksWarehouseRepository socksWarehouseRepository;

    @Override
    public void incomeSocks(SocksDto socksDto) {

    }

    @Override
    public void outcomeSocks() {

    }

    @Override
    public int getSocks() {
        return 0;
    }

    private SocksEntity findSocks() {
        return null;
    }
}
