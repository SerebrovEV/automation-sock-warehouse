package com.skypro.warehouse.socks.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


/**
 * Сущность Socks для базы данных
 */
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

    @NotBlank
    private String color;

    @Min(0)
    @Max(100)
    private Integer cottonPart;

    @Min(1)
    private Integer quantity;
}
