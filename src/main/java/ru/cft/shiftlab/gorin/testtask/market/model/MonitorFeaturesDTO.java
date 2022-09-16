package ru.cft.shiftlab.gorin.testtask.market.model;

import javax.persistence.Column;

public class MonitorFeaturesDTO extends ProductFeaturesDTO {
    private int diagonalSize;

    public int getDiagonalSize() {
        return diagonalSize;
    }

    public void setDiagonalSize(int diagonalSize) {
        this.diagonalSize = diagonalSize;
    }
}
