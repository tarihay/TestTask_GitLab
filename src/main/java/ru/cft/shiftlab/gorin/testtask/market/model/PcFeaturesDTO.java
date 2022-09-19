package ru.cft.shiftlab.gorin.testtask.market.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.PcFormFactors;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.PcEntity;

/**
 * Data Transfer Object, который отправляется с post-запросом при работе с ПК
 * Поля DTO совпадают с сущностью ПК
 * @see PcEntity
 */
public class PcFeaturesDTO extends ProductFeaturesDTO {
    private PcFormFactors formFactor;

    public PcFormFactors getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(PcFormFactors formFactor) {
        this.formFactor = formFactor;
    }


    @Override
    public String toString() {
        return "PcFeaturesDTO {" +
                "formFactor=" + formFactor +
                '}';
    }
}
