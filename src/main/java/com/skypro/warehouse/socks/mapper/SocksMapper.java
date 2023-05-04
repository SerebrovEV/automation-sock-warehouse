package com.skypro.warehouse.socks.mapper;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper {

    public SocksEntity dtoToEntity(SocksDto socksDto) {
        return SocksEntity.builder()
                .color(socksDto.getColor())
                .cottonPart(socksDto.getCottonPart())
                .quantity(socksDto.getQuantity()).build();
    }

}
