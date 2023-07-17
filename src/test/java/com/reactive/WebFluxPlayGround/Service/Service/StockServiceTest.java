package com.reactive.WebFluxPlayGround.Service.Service;

import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Service.StockService;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//FIXME: Injection 시 null 값나서 test 안됨
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;

    @MockBean
    private ReactiveValueOperations<String, Stock> reactiveValueOps;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void getStocksTest() throws IOException {
        // Load the dummy data from JSON file
        InputStreamReader reader = new InputStreamReader(resourceLoader.getResource("classpath:stock.json").getInputStream());
        List<Stock> stockList = new Gson().fromJson(reader, new TypeToken<List<Stock>>(){}.getType());
        Stock stock = stockList.get(0);

        when(reactiveRedisTemplate.keys("stock:*"))
            .thenReturn(Flux.just("stock:Test Stock"));
        when(reactiveValueOps.get("stock:Test Stock"))
            .thenReturn(Mono.just(stock));

        Flux<Stock> stocks = stockService.getStocks();

        StepVerifier.create(stocks)
            .expectNext(stock)
            .verifyComplete();
    }

    // ... other test cases ...
}
