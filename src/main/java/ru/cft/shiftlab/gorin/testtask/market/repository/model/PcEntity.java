package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "personal_computers")
public class PcEntity extends ProductEntity{
    @Column(name = "form_factor")
    private String formFactor;

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
