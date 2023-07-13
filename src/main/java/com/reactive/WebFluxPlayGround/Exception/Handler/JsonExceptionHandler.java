package com.reactive.WebFluxPlayGround.Exception.Handler;

import com.reactive.WebFluxPlayGround.Exception.JsonProcessingException;
import com.reactive.WebFluxPlayGround.Exception.StockLoadingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class JsonExceptionHandler {

    @ExceptionHandler(StockLoadingException.class)
    public ResponseEntity<String> handleStockLoadingException(StockLoadingException e) {
        log.error("Exception occurred while loading stocks", e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error occurred while loading stocks");
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonReadException(JsonProcessingException e) {
        log.error("Exception occurred while processing JSON", e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
    }
}
