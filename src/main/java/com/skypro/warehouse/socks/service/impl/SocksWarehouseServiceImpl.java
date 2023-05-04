package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.mapper.SocksMapper;
import com.skypro.warehouse.socks.repository.SocksWarehouseRepository;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksWarehouseServiceImpl implements SocksWarehouseService {
    private final SocksWarehouseRepository socksWarehouseRepository;
    private final SocksMapper socksMapper;

    @Override
    public void incomeSocks(SocksDto socksDto) {
        Optional<SocksEntity> findSocks = findSocks(socksDto.getColor(), socksDto.getCottonPart());
        if (findSocks.isEmpty()) {
            socksWarehouseRepository.save(socksMapper.dtoToEntity(socksDto));
        } else {
            SocksEntity incomeSocks = findSocks.get();
            incomeSocks.setQuantity(incomeSocks.getQuantity() + socksDto.getQuantity());
            socksWarehouseRepository.save(incomeSocks);
        }
    }

    @Override
    public void outcomeSocks(SocksDto socksDto) {
        Optional<SocksEntity> findSocks = findSocks(socksDto.getColor(), socksDto.getCottonPart());
        if (findSocks.isPresent()) {
            SocksEntity incomeSocks = findSocks.get();
            incomeSocks.setQuantity(incomeSocks.getQuantity() - socksDto.getQuantity());
            socksWarehouseRepository.save(incomeSocks);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public int getSocks(String color, String operation, Integer cottonPart) {
        switch (operation) {
            case ("lessThan"):
                return socksWarehouseRepository.getQuantityByColorAndLessCotton(color, cottonPart);
            case ("equals"):
                return socksWarehouseRepository.getQuantityByColorAndEqualsCotton(color, cottonPart);
            case ("moreThan"):
                return socksWarehouseRepository.getQuantityByColorAndMoreCotton(color, cottonPart);
            default:
                throw new RuntimeException();
        }
    }

    private Optional<SocksEntity> findSocks(String color, Integer cottonPart) {
        return socksWarehouseRepository.findByColorAndAndCottonPart(color, cottonPart);
    }
}
