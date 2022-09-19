package ru.cft.shiftlab.gorin.testtask.market.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.MemoryVolumeOptions;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.HddEntity;

/**
 * Data Transfer Object, который отправляется с post-запросом при работе с Жестким Диском
 * Поля DTO совпадают с сущностью жесткого диска
 * @see HddEntity
 */
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


    @Override
    public String toString() {
        return "HddFeaturesDTO {" +
                "memoryVolumeValue=" + memoryVolumeValue +
                ", memoryVolume=" + memoryVolume +
                '}';
    }
}
