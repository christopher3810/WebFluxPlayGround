package com.reactive.WebFluxPlayGround.Controller;

import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("")
    public Flux<Stock> getAllStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/{name}")
    public Mono<Stock> getStockByName(@PathVariable String name) {
        return stockService.getStockByName(name);
    }
}
