package com.skypro.warehouse.socks.mapper;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper для преобразования {@link SocksDto} и {@link SocksEntity}
 */
@Component
public class SocksMapper {
    /**
     * Метод для преобразования {@link SocksDto} в {@link SocksEntity}
     * @param socksDto
     * @return  {@link SocksEntity}
     */
    public SocksEntity dtoToEntity(SocksDto socksDto) {
        return SocksEntity.builder()
                .color(socksDto.getColor())
                .cottonPart(socksDto.getCottonPart())
                .quantity(socksDto.getQuantity()).build();
    }

}
