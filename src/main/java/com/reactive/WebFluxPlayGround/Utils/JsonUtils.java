package com.reactive.WebFluxPlayGround.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Exception.JsonProcessingException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.core.io.ResourceLoader;

/**
 * JsonUtils는 JSON 파일에서 읽어오는 메소드를 제공하는 유틸리티 클래스입니다.
 *
 * 이 어플리케이션에서는 초기 데이터를 JSON 파일에서 읽어와 애플리케이션에 로드하는 데 사용됩니다.
 * JSON을 파싱하고 이를 Java 객체로 변환하기 위해 Gson 라이브러리를 사용합니다.
 * JSON 데이터를 Java 객체로 파싱해야 하는 경우에는 언제든지 사용해야 합니다.
 */
public class JsonUtils {

    private static final Gson gson = new Gson();

    /**
     * Read from json list.
     *
     * @param <T>            the type parameter
     * @param resourceLoader the resource loader
     * @param resourcePath   the resource path
     * @param typeToken      the type token
     * @return the list
     */
    public static <T> List<T> readFromJson(ResourceLoader resourceLoader, String resourcePath, TypeToken<List<T>> typeToken) {
        try (InputStreamReader reader = new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream())) {
            Type type = typeToken.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new JsonProcessingException("Failed to load JSON data from " + resourcePath, e);
        }
    }
}
