package com.skypro.warehouse.socks.service;

import com.skypro.warehouse.socks.dto.SocksDto;

/**
 * Интерфейс для сервиса валидации входящих запросов
 */
public interface ValidatorSocksRequest {

    /**
     * Валидация запроса с {@link SocksDto}
     * @param socksDto входящий Json на контроллер для работы с базой данных
     */
    void validateSocksRequest(SocksDto socksDto);

    /**
     * Валидация запроса c параметрами
     * @param color цвет;
     * @param cottonPart процент хлопка в составе
     */
    void validateSocksRequest(String color, Integer cottonPart);

}
