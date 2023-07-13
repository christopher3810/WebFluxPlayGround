package com.reactive.WebFluxPlayGround.Config;

import com.reactive.WebFluxPlayGround.Model.Stock;
import com.reactive.WebFluxPlayGround.Utils.GsonRedisserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // Lettuce는 비동기로 Redis 서버에 연결하고 명령을 수행할 수 있는 Redis 클라이언트 라이브러리입니다.
    // Spring Boot가 Reactive 프로그래밍과 함께 Redis를 지원하기 위해 Lettuce를 사용합니다.
    // 여기서 생성한 LettuceConnectionFactory는 ReactiveRedisTemplate에 사용됩니다.
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public GsonRedisserializer<Stock> gsonRedisSerializer() {
        return new GsonRedisserializer<>(Stock.class);
    }

    // ReactiveRedisTemplate은 Reactive 스타일로 Redis 서버와 상호 작용할 수 있는 도구를 제공합니다.
    // Spring Data Redis에서는 모든 Redis 작업을 위한 고수준 추상화를 제공하는데, 이를 사용하여 기본 Redis 기능에 접근하고 개별 도메인 모델에 맞게 데이터를 관리할 수 있습니다.
    // 현재 프로젝트에서는 Stock 객체를 저장하고 검색하는 기능이 필요하므로, Generic Argument로 Stock 클래스를 사용하였습니다.
    @Bean
    public ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory, GsonRedisserializer<Stock> valueSerializer) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        RedisSerializationContext<String, Stock> serializationContext = RedisSerializationContext
            .<String, Stock>newSerializationContext(keySerializer)
            .value(valueSerializer)
            .hashKey(keySerializer)
            .hashValue(valueSerializer)
            .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    public ReactiveValueOperations<String, Stock> reactiveValueOps(ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate) {
        // ReactiveValueOperations bean을 생성합니다.
        // ReactiveValueOperations 인터페이스는 비동기 방식으로 Redis string (or value) operations를 수행하는 메소드를 제공합니다.
        // 이 프로젝트에서는 Stock 데이터를 Redis에 저장하고 조회하는데 사용됩니다.
        // 이를테면, 초기에 JSON 데이터를 읽어서 Redis에 저장할 때, 그리고 클라이언트 요청에 따라 해당 Stock 데이터를 Redis에서 조회할 때 등에 사용됩니다.
        return reactiveRedisTemplate.opsForValue();
    }

}
