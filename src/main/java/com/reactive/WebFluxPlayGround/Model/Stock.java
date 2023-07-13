package com.reactive.WebFluxPlayGround.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Stock {
    private String name;
    private String industry;
    private String headquarters;
    private String ceo;
    private String founded;
    private String marketCap;
    private String lastPrice;
    private String lastRSI;
    private String lastMoneyFlow;
}