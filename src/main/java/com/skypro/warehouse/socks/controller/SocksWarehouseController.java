package com.skypro.warehouse.socks.controller;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.model.Operation;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/socks")
@RequiredArgsConstructor
public class SocksWarehouseController {
    private final SocksWarehouseService socksWarehouseService;

    @PostMapping("/income")
    public ResponseEntity incomeSocks(@RequestBody SocksDto socksDto) {
        socksWarehouseService.incomeSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity outcomeSocks(@RequestBody SocksDto socksDto) {
        socksWarehouseService.outcomeSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam String color,
                                            @RequestParam Operation operation,
                                            @RequestParam Integer cottonPart) {
        return ResponseEntity.ok(socksWarehouseService.getSocks(color, operation, cottonPart));
    }



}
