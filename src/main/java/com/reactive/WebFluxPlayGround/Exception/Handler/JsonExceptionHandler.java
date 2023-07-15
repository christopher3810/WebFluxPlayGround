package com.reactive.WebFluxPlayGround.Exception.Handler;

import com.reactive.WebFluxPlayGround.Exception.JsonProcessingException;
import com.reactive.WebFluxPlayGround.Exception.StockLoadingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
@Slf4j
public class JsonExceptionHandler {

    @ExceptionHandler({StockLoadingException.class, RedisConnectionFailureException.class})
    public ResponseEntity<String> handleStockLoadingException(Exception e) {
        log.error("Exception occurred while loading stocks", e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error occurred while loading stocks");
    }

    @ExceptionHandler({JsonProcessingException.class, WebClientResponseException.class})
    public ResponseEntity<String> handleJsonReadException(Exception e) {
        log.error("Exception occurred while processing JSON", e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
    }
}
