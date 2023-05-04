package com.skypro.warehouse.socks.entity;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocksEntity {
    private int id;
    private String name;
    private int cottonPart;
    private int quantity;
}
