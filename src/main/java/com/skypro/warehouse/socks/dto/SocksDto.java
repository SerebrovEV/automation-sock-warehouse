package com.skypro.warehouse.socks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocksDto {
    private String color;
    private int cottonPart;
    private int quantity;
}
