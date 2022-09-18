package ru.cft.shiftlab.gorin.testtask.market.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.MemoryVolumeOptions;

import javax.persistence.Column;

public class HddFeaturesDTO extends ProductFeaturesDTO {
    private int memoryVolumeValue;

    private MemoryVolumeOptions memoryVolume;

    public int getMemoryVolumeValue() {
        return memoryVolumeValue;
    }

    public void setMemoryVolumeValue(int memoryVolumeValue) {
        this.memoryVolumeValue = memoryVolumeValue;
    }

    public MemoryVolumeOptions getMemoryVolume() {
        return memoryVolume;
    }

    public void setMemoryVolume(MemoryVolumeOptions memoryVolume) {
        this.memoryVolume = memoryVolume;
    }

}
