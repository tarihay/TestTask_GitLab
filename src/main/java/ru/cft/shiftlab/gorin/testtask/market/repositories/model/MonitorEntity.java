package ru.cft.shiftlab.gorin.testtask.market.repositories.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "monitors")
public class MonitorEntity extends ProductEntity{
    @NotNull
    @Column(name = "diagonal_size")
    private int diagonalSize;


    public int getDiagonalSize() {
        return diagonalSize;
    }

    public void setDiagonalSize(int diagonalSize) {
        this.diagonalSize = diagonalSize;
    }
}
