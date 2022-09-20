package ru.cft.shiftlab.gorin.market.exceptions;

/**
 * Исключение, вызванное нахождением большего количества записей, чем предполагалось
 */
public class TooManyRecordsFoundException extends Exception {
    public TooManyRecordsFoundException() {
        super();
    }
}
