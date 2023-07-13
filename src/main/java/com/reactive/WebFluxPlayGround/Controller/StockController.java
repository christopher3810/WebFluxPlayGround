package com.reactive.WebFluxPlayGround.Controller;

import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stocks")
    public Flux<Stock> getStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/stocks/{name}")
    public Flux<Stock> getStockByName(@PathVariable String name) {
        return stockService.getStockByName(name);
    }
}
