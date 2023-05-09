package com.skypro.warehouse.socks.service.impl;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.exception.BadRequestException;
import com.skypro.warehouse.socks.exception.SocksNotFoundException;
import com.skypro.warehouse.socks.mapper.SocksMapper;
import com.skypro.warehouse.socks.model.Operation;
import com.skypro.warehouse.socks.repository.SocksWarehouseRepository;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import com.skypro.warehouse.socks.service.ValidatorSocksRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;
import java.util.Optional;


/**
 * Имплементация интерфейса {@link SocksWarehouseService}
 */
@Service
@RequiredArgsConstructor
@Log4j
public class SocksWarehouseServiceImpl implements SocksWarehouseService {
    private final SocksWarehouseRepository socksWarehouseRepository;
    private final SocksMapper socksMapper;
    private final ValidatorSocksRequest validatorSocksRequest;

    /**
     * Метод для добавления пар носков {@link SocksEntity} в базу данных
     *
     * @param socksDto входящий Json на контроллер для работы с базой данных
     */
    @Override
    public void incomeSocks(SocksDto socksDto) {
        validatorSocksRequest.validateSocksRequest(socksDto);
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

    /**
     * Метод для уменьшения количества пар носков {@link SocksEntity} в базе данных
     *
     * @param socksDto входящий Json на контроллер для работы с базой данных
     */
    @Override
    public void outcomeSocks(SocksDto socksDto) {
        validatorSocksRequest.validateSocksRequest(socksDto);
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

    /**
     * Метод для получения количества пар носков {@link SocksEntity} в базе данных по параметрам
     *
     * @param color      цвет;
     * @param operation  операция сравнения;
     * @param cottonPart процент хлопка в составе;
     * @return {@link String} суммарное количество пар носков
     */
    @Override
    public String getSocks(String color, Operation operation, Integer cottonPart) {
        validatorSocksRequest.validateSocksRequest(color, cottonPart);
        log.info("Request about quantity pair of socks");
        Integer quantitySocks;
        switch (operation) {
            case lessThan:
                quantitySocks = socksWarehouseRepository.getQuantityByColorAndLessCotton(color, cottonPart);
                break;
            case equal:
                quantitySocks = socksWarehouseRepository.getQuantityByColorAndEqualsCotton(color, cottonPart);
                break;
            case moreThan:
                quantitySocks = socksWarehouseRepository.getQuantityByColorAndMoreCotton(color, cottonPart);
                break;
            default:
                throw new BadRequestException();
        }
        return Objects.requireNonNullElse(quantitySocks, 0).toString();
    }

    /**
     * Метод для поиска {@link Optional} <{@link SocksEntity}> в базе данных по параметрам
     *
     * @param color      цвет носков;
     * @param cottonPart процент хлопка в составе;
     * @return {@link Optional} <{@link SocksEntity}>
     */
    private Optional<SocksEntity> findSocks(String color, Integer cottonPart) {
        log.info("Searching item pair of socks on the warehouse");
        return socksWarehouseRepository.findByColorAndAndCottonPart(color, cottonPart);
    }
}

