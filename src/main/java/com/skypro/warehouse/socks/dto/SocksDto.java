package com.skypro.warehouse.socks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocksDto {
    private String name;
    private int cottonPart;
    private int quantity;
}
