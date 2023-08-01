package com.reactive.WebFluxPlayGround.Exception;

public class JsonFileReadingException extends JsonProcessingException{

    private final String filePath;

    public JsonFileReadingException(String message, Throwable cause, String filePath) {
        super(message, cause);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
