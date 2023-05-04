package com.skypro.warehouse.socks.mapper;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.entity.SocksEntity;

public class SocksMapper {

    public SocksEntity dtoToEntity(SocksDto socksDto) {
        return SocksEntity.builder()
                .name(socksDto.getName())
                .cottonPart(socksDto.getCottonPart())
                .quantity(socksDto.getQuantity()).build();
    }

}
