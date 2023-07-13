package com.reactive.WebFluxPlayGround.Service;

import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Utils.JsonUtils;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import reactor.test.StepVerifier;

class StockServiceTest {

    private StockService stockService;

    @BeforeEach
    public void setUp() {
        // Prepare test data
        Stock stock1 = new Stock("ABC", "Tech", "USA", "CEO1", "1990", "500B", "$200", "60", "$1000");
        Stock stock2 = new Stock("XYZ", "Tech", "USA", "CEO2", "2000", "200B", "$100", "70", "$500");
        List<Stock> testData = Arrays.asList(stock1, stock2);

        // Mock ResourceLoader
        ResourceLoader resourceLoader = Mockito.mock(ResourceLoader.class);
        Resource resource = Mockito.mock(Resource.class);

        // Mock JsonUtils
        Mockito.when(resourceLoader.getResource(Mockito.anyString())).thenReturn(resource);

        Mockito.when(JsonUtils.readFromJson(resourceLoader, "classpath:stocks.json", new TypeToken<List<Stock>>(){}))
            .thenReturn(testData);

        // Create StockService
        stockService = new StockService(resourceLoader);
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