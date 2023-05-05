package com.skypro.warehouse.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Параметры запроса отсутствуют или имеют некорректный формат");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SocksNotFoundException.class)
    public ResponseEntity<String> handleSocksNotFoundException(SocksNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.format("Носков c цветом = %s  и процентом хлопка = %d на складе не найдено", e.getColor(), e.getCottonPart()));
    }
}
