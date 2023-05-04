package com.skypro.warehouse.socks.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "socks")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String color;
    private Integer cottonPart;
    private Integer quantity;
}
