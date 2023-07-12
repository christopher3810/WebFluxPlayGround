package com.reactive.WebFluxPlayGround.Model;

import lombok.Getter;
import org.springframework.scheduling.annotation.EnableScheduling;

@Getter
public class Stock {
    private String name;
    private String industry;
    private String headquarters;
    private String ceo;
    private String founded;

    // getters and setters
}