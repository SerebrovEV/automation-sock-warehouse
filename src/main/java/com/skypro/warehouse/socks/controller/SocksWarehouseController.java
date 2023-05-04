package com.skypro.warehouse.socks.controller;

import com.skypro.warehouse.socks.dto.SocksDto;
import com.skypro.warehouse.socks.service.SocksWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam String color,
                                            @RequestParam String operation,
                                            @RequestParam int cottonPart) {
        return null;
    }



}
