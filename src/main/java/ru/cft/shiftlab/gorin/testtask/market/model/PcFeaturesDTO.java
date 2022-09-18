package ru.cft.shiftlab.gorin.testtask.market.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.PcFormFactors;

import javax.persistence.Column;

public class PcFeaturesDTO extends ProductFeaturesDTO {
    private PcFormFactors formFactor;

    public PcFormFactors getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(PcFormFactors formFactor) {
        this.formFactor = formFactor;
    }
}
