package ru.cft.shiftlab.gorin.testtask.market.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.LaptopSize;

import javax.persistence.Column;

public class LaptopFeaturesDTO extends ProductFeaturesDTO {
    private LaptopSize size;

    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }
}
