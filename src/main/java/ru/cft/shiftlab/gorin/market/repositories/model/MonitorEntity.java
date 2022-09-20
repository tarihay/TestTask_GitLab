package ru.cft.shiftlab.gorin.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.market.model.enums.ProductType;

import javax.persistence.*;

/**
 * Сущность монитора. Наследуется от сущности товара.
 * Поля хранятся в таблице "monitors"
 * @see ProductEntity
 */
@Entity
@Table(name = "monitors")
public class MonitorEntity extends ProductEntity{
    @NotNull
    @Column(name = "diagonal_size")
    private int diagonalSize;

    public MonitorEntity(String serialNumber, String manufacturer, long priceValue, int amount, ProductType productType, int diagonalSize) {
        super(serialNumber, manufacturer, priceValue, amount, productType);
        this.diagonalSize = diagonalSize;
    }

    public MonitorEntity() {

    }

    public int getDiagonalSize() {
        return diagonalSize;
    }

    public void setDiagonalSize(int diagonalSize) {
        this.diagonalSize = diagonalSize;
    }

    @Override
    public String toString() {
        return "MonitorEntity{ " +
                "diagonalSize=" + diagonalSize +
                '}';
    }
}
