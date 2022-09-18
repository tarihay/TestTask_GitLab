package ru.cft.shiftlab.gorin.testtask.market.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonParsingException extends JsonProcessingException {

    public JsonParsingException(String msg) {
        super(msg);
    }
}
