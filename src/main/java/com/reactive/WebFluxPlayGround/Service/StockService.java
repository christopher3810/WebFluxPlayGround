package com.reactive.WebFluxPlayGround.Service;

import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Utils.JsonUtils;
import java.util.List;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StockService {

    private final ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;

    public StockService(ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    public Flux<Stock> getStocks() {
        return reactiveRedisTemplate.opsForHash().values("stocks");
    }

    public Mono<Stock> getStockByName(String name) {
        return reactiveRedisTemplate.opsForHash().get("stocks", name);
    }
}
