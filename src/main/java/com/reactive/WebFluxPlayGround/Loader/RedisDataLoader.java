package com.reactive.WebFluxPlayGround.Loader;

import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Utils.JsonUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisDataLoader {

    private final ResourceLoader resourceLoader;
    private final ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
        List<Stock> stockList = JsonUtils.readFromJson(resourceLoader, "classpath:stocks.json", new TypeToken<List<Stock>>(){});
        stockList.forEach(stock -> reactiveRedisTemplate.opsForHash().put("stocks", stock.getName(), stock).subscribe());
    }

}
