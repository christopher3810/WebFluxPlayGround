package com.reactive.WebFluxPlayGround.Exception;

public class JsonProcessingException extends RuntimeException {
    public JsonProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
