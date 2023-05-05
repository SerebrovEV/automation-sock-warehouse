package com.skypro.warehouse.socks.exception;

import com.skypro.warehouse.socks.entity.SocksEntity;

/**
 * Exception при отсутсвии {@link SocksEntity } в базе данных
 */
public class SocksNotFoundException extends RuntimeException{

    private final String color;
    private final int cottonPart;

    public SocksNotFoundException(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }
}
