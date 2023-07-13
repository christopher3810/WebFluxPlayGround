package com.reactive.WebFluxPlayGround.Utils;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * GsonRedisSerializer는 Gson 라이브러리를 사용하여 Java 객체를 JSON 문자열로 직렬화하고
 * 역직렬화하는 메소드를 제공하는 유틸리티 클래스입니다. 이 클래스는 이 어플리케이션의 Redis 데이터베이스와 함께 사용하기 위해 만들어졌습니다.
 *
 * Redis에서 객체를 저장할 때는, 이들을 문자열로 저장할 수 있는 형식으로 직렬화해야 합니다.
 * 왜냐하면 Redis는 데이터를 문자열로 저장하는 키-값 데이터베이스이기 때문입니다.
 * 비슷하게, Redis에서 객체를 검색할 때는, 이들을 다시 Java 객체로 역직렬화해야 합니다.
 *
 * 이 클래스는 직렬화와 역직렬화 메소드를 정의하는 RedisSerializer 인터페이스를 구현합니다.
 * Gson을 이용함으로써, 객체가 정확하게 직렬화와 역직렬화될 수 있음을 보장할 수 있습니다.
 */
@RequiredArgsConstructor
public class GsonRedisserializer<T> implements RedisSerializer<T> {
    private final Class<T> clazz;
    private final Gson gson;

    public GsonRedisserializer(Class<T> clazz) {
        this.clazz = clazz;
        this.gson = new Gson();
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return gson.toJson(t).getBytes();
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return gson.fromJson(new String(bytes), clazz);
    }

}
