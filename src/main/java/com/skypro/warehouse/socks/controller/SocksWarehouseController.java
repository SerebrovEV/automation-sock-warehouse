package com.skypro.warehouse.socks.controller;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.model.Operation;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с HTTP-запросами по url "/socks..."
 */
@RestController
@RequestMapping("/socks")
@RequiredArgsConstructor
public class SocksWarehouseController {
    private final SocksWarehouseService socksWarehouseService;


    @io.swagger.v3.oas.annotations.Operation(
            summary = "IncomeSocks",
            description = "Добавление пар носков на склад",
            tags = {"Socks"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Добавление пар носков на склад",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )),

            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "400", description = "Incorrect request"),
                    @ApiResponse(responseCode = "500", description = "An error occurred that is not dependent on the caller")
            })
    @PostMapping("/income")
    public ResponseEntity incomeSocks(@RequestBody SocksDto socksDto) {
        socksWarehouseService.incomeSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @io.swagger.v3.oas.annotations.Operation(
            summary = "OutcomeSocks",
            description = "Отпуск пар носков со склада",
            tags = {"Socks"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Отпуск пар носков со склада",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "400", description = "Incorrect request"),
                    @ApiResponse(responseCode = "500", description = "An error occurred that is not dependent on the caller")
            })
    @PostMapping("/outcome")
    public ResponseEntity<Void>  outcomeSocks(@RequestBody SocksDto socksDto) {
        socksWarehouseService.outcomeSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @io.swagger.v3.oas.annotations.Operation(
            summary = "GetSocks",
            description = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса",
            tags = {"Socks"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "400", description = "Incorrect request"),
                    @ApiResponse(responseCode = "500", description = "An error occurred that is not dependent on the caller")
            })
    @GetMapping
    public ResponseEntity<String> getSocks(@Parameter(
                                                    description = "Цвет носков", example = "Red")
                                            @RequestParam String color,
                                            @Parameter(
                                                    description = "Оператор сравнения значения количества хлопка в составе носков", example = "lessThan")
                                            @RequestParam Operation operation,
                                            @Parameter(
                                                    description = "Значение процента хлопка", example = "10")
                                            @RequestParam Integer cottonPart) {
        return ResponseEntity.ok(socksWarehouseService.getSocks(color, operation, cottonPart));
    }

}
