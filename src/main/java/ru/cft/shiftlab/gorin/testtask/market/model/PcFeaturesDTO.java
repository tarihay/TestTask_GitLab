package ru.cft.shiftlab.gorin.testtask.market.model;

import javax.persistence.Column;

public class PcFeaturesDTO extends ProductFeaturesDTO {
    private String formFactor;

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
