package ru.cft.shiftlab.gorin.testtask.market.model;


import ru.cft.shiftlab.gorin.testtask.market.repositories.model.MonitorEntity;

/**
 * Data Transfer Object, который отправляется с post-запросом при работе с монитором
 * Поля DTO совпадают с сущностью монитора
 * @see MonitorEntity
 */
public class MonitorFeaturesDTO extends ProductFeaturesDTO {
    private int diagonalSize;

    public int getDiagonalSize() {
        return diagonalSize;
    }

    public void setDiagonalSize(int diagonalSize) {
        this.diagonalSize = diagonalSize;
    }

    @Override
    public String toString() {
        return "MonitorFeaturesDTO {" +
                "diagonalSize=" + diagonalSize +
                '}';
    }
}
