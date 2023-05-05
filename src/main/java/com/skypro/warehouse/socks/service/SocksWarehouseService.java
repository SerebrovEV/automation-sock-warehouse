package com.skypro.warehouse.socks.service;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import com.skypro.warehouse.socks.model.Operation;

/**
 * Интерфейс для работы с {@link SocksDto}
 */
public interface SocksWarehouseService {

    /**
     * Метод для добавления количества пар носков в базу данных
     * @param socksDto
     */
    void incomeSocks(SocksDto socksDto);

    /**
     * Метод для уменьшения количества пар носков в базу данных
     * @param socksDto
     */
    void outcomeSocks(SocksDto socksDto);

    /**
     * Метод для получения количества пар носков {@link SocksEntity} в базе данных по параметрам
     * @param color цвет;
     * @param operation операция сравнения;
     * @param cottonPart процент хлопка в составе;
     * @return {@link Integer} суммарное количество пар носков
     */
    int getSocks(String color, Operation operation, Integer cottonPart);
}
