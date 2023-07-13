package com.reactive.WebFluxPlayGround.Service;

import com.reactive.WebFluxPlayGround.Model.Stock;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StockService {


    private final ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;
    private final ReactiveValueOperations<String, Stock> reactiveValueOps;

    public StockService(ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
        this.reactiveValueOps = reactiveRedisTemplate.opsForValue();
    }

    public Flux<Stock> getStocks() {
        return reactiveRedisTemplate.keys("stock:*")
            .flatMap(reactiveValueOps::get);
    }

    public Mono<Stock> getStockByName(String name) {
        return reactiveValueOps.get("stock:" + name);
    }
}
