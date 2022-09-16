package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "hard_disk_drives")
public class HddEntity extends ProductEntity {
    @Column(name = "memory_volume_value")
    private int memoryVolumeValue;

    @Column(name = "memory_volume")
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
