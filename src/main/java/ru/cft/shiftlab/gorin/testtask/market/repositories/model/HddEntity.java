package ru.cft.shiftlab.gorin.testtask.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.testtask.market.model.enums.MemoryVolumeOptions;

import javax.persistence.*;

@Entity
@Table(name = "hard_disk_drives")
public class HddEntity extends ProductEntity {
    @NotNull
    @Column(name = "memory_volume_value")
    private int memoryVolumeValue;

    @NotNull
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
