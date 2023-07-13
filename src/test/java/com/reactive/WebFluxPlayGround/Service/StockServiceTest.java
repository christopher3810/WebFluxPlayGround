package com.reactive.WebFluxPlayGround.Service;

import static org.mockito.Mockito.when;

import com.reactive.WebFluxPlayGround.Model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//FIXME: Injection 시 null 값나서 test 안됨
@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private ReactiveValueOperations<String, Stock> reactiveValueOperations;

    @BeforeEach
    public void setUp() {
        // Prepare test data
        Stock stock1 = new Stock("ABC", "Tech", "USA", "CEO1", "1990", "500B", "$200", "60", "$1000");
        Stock stock2 = new Stock("XYZ", "Tech", "USA", "CEO2", "2000", "200B", "$100", "70", "$500");

        // Mock the ReactiveValueOperations
        when(reactiveValueOperations.get("stock:" + stock1.getName())).thenReturn(Mono.just(stock1));
        when(reactiveValueOperations.get("stock:" + stock2.getName())).thenReturn(Mono.just(stock2));
    }

    @Test
    public void testGetStocks() {
        // Call getStocks() and verify that it returns all stocks
        StepVerifier.create(stockService.getStocks())
            .expectNextMatches(stock -> "ABC".equals(stock.getName()))
            .expectNextMatches(stock -> "XYZ".equals(stock.getName()))
            .verifyComplete();
    }

    @Test
    public void testGetStockByName() {
        // Call getStockByName() and verify that it returns the correct stock
        StepVerifier.create(stockService.getStockByName("XYZ"))
            .expectNextMatches(stock -> "XYZ".equals(stock.getName()))
            .verifyComplete();
    }
}