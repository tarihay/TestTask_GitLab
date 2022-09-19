package ru.cft.shiftlab.gorin.testtask.market.exceptions;

import java.util.NoSuchElementException;

/**
 * Исключение, вызванное ошибкой сохранения записи
 */
public class SavingRecordException extends NoSuchElementException {
    public SavingRecordException() {
        super();
    }
}
