package com.reactive.WebFluxPlayGround.Loader;

import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Utils.JsonUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisDataLoader {

    private final ResourceLoader resourceLoader;
    private final ReactiveValueOperations<String, Stock> reactiveValueOps;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData()  {
        List<Stock> stockList = JsonUtils.readFromJson(resourceLoader, "classpath:stock.json", new TypeToken<List<Stock>>(){});
        stockList.forEach(stock -> reactiveValueOps.set("stock:" + stock.getName(), stock).subscribe());
    }

}
