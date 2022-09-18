package ru.cft.shiftlab.gorin.testtask.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.testtask.market.model.enums.LaptopSize;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class LaptopEntity extends ProductEntity{
    @NotNull
    @Column(name = "size")
    private LaptopSize size;

    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }
}
