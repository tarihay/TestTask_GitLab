package ru.cft.shiftlab.gorin.market.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Исключение, вызванное ошибкой парсинга JSON-объекта
 */
public class JsonParsingException extends JsonProcessingException {
    public JsonParsingException(String msg) {
        super(msg);
    }
}
