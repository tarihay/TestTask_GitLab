package ru.cft.shiftlab.gorin.testtask.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.testtask.market.model.enums.PcFormFactors;

import javax.persistence.*;

/**
 * Сущность ПК. Наследуется от сущности товара.
 * Поля хранятся в таблице "personal_computers"
 * @see ProductEntity
 */
@Entity
@Table(name = "personal_computers")
public class PcEntity extends ProductEntity{
    @NotNull
    @Column(name = "form_factor")
    private PcFormFactors formFactor;

    public PcFormFactors getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(PcFormFactors formFactor) {
        this.formFactor = formFactor;
    }

    @Override
    public String toString() {
        return "PcEntity{ " +
                "formFactor=" + formFactor +
                '}';
    }
}
