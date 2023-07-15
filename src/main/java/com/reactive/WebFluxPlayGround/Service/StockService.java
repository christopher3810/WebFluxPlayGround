package com.reactive.WebFluxPlayGround.Service;

import com.reactive.WebFluxPlayGround.Exception.StockLoadingException;
import com.reactive.WebFluxPlayGround.Exception.StockNotFoundException;
import com.reactive.WebFluxPlayGround.Model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 주식 서비스 클래스입니다.
 * Redis에 저장된 주식 데이터를 조회하는 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class StockService {


    private final ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;
    private final ReactiveValueOperations<String, Stock> reactiveValueOps;


    /**
     * 모든 주식 정보를 조회합니다.
     * @return 주식 정보를 담은 Flux
     */
    public Flux<Stock> getStocks() {
        return reactiveRedisTemplate.keys("stock:*")
            .flatMap(key -> reactiveValueOps.get(key)
                .onErrorResume(e -> Mono.error(new StockLoadingException("Failed to load stock: " + key, e))));
    }

    /**
     * 주식 이름으로 주식 정보를 조회합니다.
     * @param name 주식 이름
     * @return 주식 정보를 담은 Mono
     */
    public Mono<Stock> getStockByName(String name) {
        return reactiveValueOps.get("stock:" + name)
            .switchIfEmpty(Mono.error(new StockNotFoundException("Stock not found: " + name)))
            .onErrorResume(StockNotFoundException.class, e -> Mono.error(e))
            .onErrorResume(e -> Mono.error(new StockLoadingException("Failed to load stock: " + name, e)));
    }

}
