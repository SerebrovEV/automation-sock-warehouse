package com.skypro.warehouse.socks.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@EqualsAndHashCode
public class SocksDto {
    @NotBlank
    private String color;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @PositiveOrZero
    private int quantity;
}
