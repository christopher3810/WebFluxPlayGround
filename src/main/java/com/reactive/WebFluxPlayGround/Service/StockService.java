package com.reactive.WebFluxPlayGround.Service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.WebFluxPlayGround.Model.Stock;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StockService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;
    private List<Stock> stockList;

    public StockService(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
        loadStocks();
    }

    private void loadStocks() {
        try {
            InputStream is = resourceLoader.getResource("classpath:stocks.json").getInputStream();
            this.stockList = objectMapper.readValue(is, new TypeReference<List<Stock>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Flux<Stock> getStocks() {
        return Flux.fromIterable(this.stockList);
    }

    public Flux<Stock> getStockByName(String name) {
        return Flux.fromIterable(this.stockList)
            .filter(stock -> stock.getName().equalsIgnoreCase(name));
    }
}
