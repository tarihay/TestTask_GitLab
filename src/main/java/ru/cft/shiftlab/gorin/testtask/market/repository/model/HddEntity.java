package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.MemoryVolumeOptions;

import javax.persistence.*;

@Entity
@Table(name = "hard_disk_drives")
public class HddEntity extends ProductEntity {
    @Column(name = "memory_volume_value")
    private int memoryVolumeValue;

    @Column(name = "memory_volume")
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
