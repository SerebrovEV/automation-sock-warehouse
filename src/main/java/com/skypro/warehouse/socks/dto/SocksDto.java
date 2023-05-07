package com.skypro.warehouse.socks.dto;

import lombok.*;

/**
 * Dto для работы с контроллером
 */
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class SocksDto {

    private String color;
    private int cottonPart;
    private int quantity;
}
