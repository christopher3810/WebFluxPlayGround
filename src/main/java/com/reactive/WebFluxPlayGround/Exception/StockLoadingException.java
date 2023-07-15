package com.reactive.WebFluxPlayGround.Exception;

import java.io.IOException;

/**
 * 주식 정보를 로딩하는 도중 발생하는 예외입니다.
 */
public class StockLoadingException extends IOException {

    public StockLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
