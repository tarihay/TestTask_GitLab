package ru.cft.shiftlab.gorin.testtask.market.model;

import javax.persistence.Column;

public class LaptopFeaturesDTO extends ProductFeaturesDTO {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
