package ru.cft.shiftlab.gorin.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.market.model.enums.LaptopSize;
import ru.cft.shiftlab.gorin.market.model.enums.ProductType;

import javax.persistence.*;

/**
 * Сущность ноутбука. Наследуется от сущности товара.
 * Поля хранятся в таблице "laptops"
 * @see ProductEntity
 */
@Entity
@Table(name = "laptops")
public class LaptopEntity extends ProductEntity{
    @NotNull
    @Column(name = "size")
    private LaptopSize size;

    public LaptopEntity(String serialNumber, String manufacturer, long priceValue, int amount, ProductType productType, LaptopSize laptopSize) {
        super(serialNumber, manufacturer, priceValue, amount, productType);
        this.size = laptopSize;
    }

    public LaptopEntity() {

    }


    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LaptopEntity {" +
                "size=" + size +
                '}';
    }
}
