package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.PcFormFactors;

import javax.persistence.*;

@Entity
@Table(name = "personal_computers")
public class PcEntity extends ProductEntity{
    @Column(name = "form_factor")
    private PcFormFactors formFactor;

    public PcFormFactors getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(PcFormFactors formFactor) {
        this.formFactor = formFactor;
    }
}
