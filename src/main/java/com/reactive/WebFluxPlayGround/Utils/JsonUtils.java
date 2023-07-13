package com.reactive.WebFluxPlayGround.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reactive.WebFluxPlayGround.Exception.JsonProcessingException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.core.io.ResourceLoader;

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
