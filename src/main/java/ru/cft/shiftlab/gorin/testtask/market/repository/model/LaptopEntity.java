package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.LaptopSize;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class LaptopEntity extends ProductEntity{
    @Column(name = "size")
    private LaptopSize size;

    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }
}
