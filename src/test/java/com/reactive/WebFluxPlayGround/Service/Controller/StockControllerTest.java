package com.reactive.WebFluxPlayGround.Service.Controller;

import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Service.StockService;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@SpringBootTest
@AutoConfigureWebTestClient
public class StockControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StockService stockService;

    @Autowired
    private ResourceLoader resourceLoader;

    private List<Stock> stockList;

    @BeforeEach
    public void setup() throws IOException {
        // Load the dummy data from JSON file
        InputStreamReader reader = new InputStreamReader(resourceLoader.getResource("classpath:stock.json").getInputStream());
        stockList = new Gson().fromJson(reader, new TypeToken<List<Stock>>(){}.getType());
    }

    @Test
    public void getAllStocksTest() {
        Stock stock = stockList.get(0);

        when(stockService.getStocks())
            .thenReturn(Flux.just(stock));

        webTestClient.get().uri("/")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Stock.class)
            .contains(stock);
    }

    // ... other test cases ...
}

