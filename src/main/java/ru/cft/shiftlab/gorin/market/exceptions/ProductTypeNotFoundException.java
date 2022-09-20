package ru.cft.shiftlab.gorin.market.exceptions;

import ru.cft.shiftlab.gorin.market.model.enums.ProductType;

/**
 * Исключение при указании типа товара, который не указан в enum-классе ProductType
 * @see ProductType
 */
public class ProductTypeNotFoundException extends Exception {
    public ProductTypeNotFoundException() {
        super();
    }
}
