package com.reactive.WebFluxPlayGround.Config;

import com.reactive.WebFluxPlayGround.Model.Stock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.base-url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    @Bean
    ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate(
        ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Stock> serializer = new Jackson2JsonRedisSerializer<>(Stock.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Stock> builder =
            RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Stock> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}
