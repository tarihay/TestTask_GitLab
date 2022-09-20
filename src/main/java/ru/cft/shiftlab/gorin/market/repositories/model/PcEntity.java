package ru.cft.shiftlab.gorin.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.market.model.enums.PcFormFactors;
import ru.cft.shiftlab.gorin.market.model.enums.ProductType;

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

    public PcEntity(String serialNumber, String manufacturer, long priceValue, int amount, ProductType productType, PcFormFactors formFactor) {
        super(serialNumber, manufacturer, priceValue, amount, productType);
        this.formFactor = formFactor;
    }

    public PcEntity() {
    }

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
