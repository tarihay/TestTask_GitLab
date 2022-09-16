package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class LaptopEntity extends ProductEntity{
    @Column(name = "size")
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
