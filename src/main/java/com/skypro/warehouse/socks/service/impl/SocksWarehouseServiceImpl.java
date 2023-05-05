package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.exception.BadRequestException;
import com.skypro.warehouse.socks.exception.SocksNotFoundException;
import com.skypro.warehouse.socks.mapper.SocksMapper;
import com.skypro.warehouse.socks.model.Operation;
import com.skypro.warehouse.socks.repository.SocksWarehouseRepository;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import com.skypro.warehouse.socks.service.ValidatorRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor
@Log4j
public class SocksWarehouseServiceImpl implements SocksWarehouseService {
    private final SocksWarehouseRepository socksWarehouseRepository;
    private final SocksMapper socksMapper;
    private final ValidatorRequest validatorRequest;

    @Override
    public void incomeSocks(SocksDto socksDto) {
        validatorRequest.validateSocksRequest(socksDto);
        Optional<SocksEntity> findSocks = findSocks(socksDto.getColor(), socksDto.getCottonPart());
        if (findSocks.isEmpty()) {
            log.info("Added new pair of socks to the warehouse");
            socksWarehouseRepository.save(socksMapper.dtoToEntity(socksDto));
        } else {
            SocksEntity incomeSocks = findSocks.get();
            incomeSocks.setQuantity(incomeSocks.getQuantity() + socksDto.getQuantity());
            log.info("Incremented quantity pair of socks on the warehouse");
            socksWarehouseRepository.save(incomeSocks);
        }
    }

    @Override
    public void outcomeSocks(SocksDto socksDto) {
        validatorRequest.validateSocksRequest(socksDto);
        Optional<SocksEntity> findSocks = findSocks(socksDto.getColor(), socksDto.getCottonPart());
        if (findSocks.isPresent()) {
            log.info("Decrement quantity socks on the warehouse");
            SocksEntity incomeSocks = findSocks.get();
            incomeSocks.setQuantity(incomeSocks.getQuantity() - socksDto.getQuantity());
            socksWarehouseRepository.save(incomeSocks);
        } else {
            log.info("Socks not found on the warehouse");
            throw new SocksNotFoundException(socksDto.getColor(), socksDto.getCottonPart());
        }
    }

    @Override
    public int getSocks(String color, Operation operation, Integer cottonPart) {
        validatorRequest.validateSocksRequest(color, cottonPart);
        log.info("Request about quantity pair of socks");
        switch (operation) {
            case lessThan:
                return socksWarehouseRepository.getQuantityByColorAndLessCotton(color, cottonPart);
            case equal:
                return socksWarehouseRepository.getQuantityByColorAndEqualsCotton(color, cottonPart);
            case moreThan:
                return socksWarehouseRepository.getQuantityByColorAndMoreCotton(color, cottonPart);
            default:
                throw new BadRequestException();
        }

    }

    private Optional<SocksEntity> findSocks(String color, Integer cottonPart) {
        log.info("Searching item pair of socks on the warehouse");
        return socksWarehouseRepository.findByColorAndAndCottonPart(color, cottonPart);
    }
}

