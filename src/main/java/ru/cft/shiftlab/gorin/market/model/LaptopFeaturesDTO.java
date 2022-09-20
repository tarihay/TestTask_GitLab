package ru.cft.shiftlab.gorin.market.model;

import ru.cft.shiftlab.gorin.market.model.enums.LaptopSize;
import ru.cft.shiftlab.gorin.market.repositories.model.LaptopEntity;


/**
 * Data Transfer Object, который отправляется с post-запросом при работе с Ноутбуком
 * Поля DTO совпадают с сущностью ноутбука
 * @see LaptopEntity
 */
public class LaptopFeaturesDTO extends ProductFeaturesDTO {
    private LaptopSize size;

    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LaptopFeaturesDTO {" +
                "size=" + size +
                '}';
    }
}
