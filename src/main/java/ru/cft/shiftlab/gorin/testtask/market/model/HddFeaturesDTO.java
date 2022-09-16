package ru.cft.shiftlab.gorin.testtask.market.model;

import javax.persistence.Column;

public class HddFeaturesDTO extends ProductFeaturesDTO {
    private int memoryVolumeValue;

    private String memoryVolume;

    public int getMemoryVolumeValue() {
        return memoryVolumeValue;
    }

    public void setMemoryVolumeValue(int memoryVolumeValue) {
        this.memoryVolumeValue = memoryVolumeValue;
    }

    public String getMemoryVolume() {
        return memoryVolume;
    }

    public void setMemoryVolume(String memoryVolume) {
        this.memoryVolume = memoryVolume;
    }

}
