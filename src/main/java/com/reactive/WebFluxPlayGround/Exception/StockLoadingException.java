package com.reactive.WebFluxPlayGround.Exception;

import java.io.IOException;

public class StockLoadingException extends IOException {

    public StockLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
