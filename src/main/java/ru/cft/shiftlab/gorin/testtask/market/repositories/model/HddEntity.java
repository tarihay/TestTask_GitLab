package ru.cft.shiftlab.gorin.testtask.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.testtask.market.model.enums.MemoryVolumeOptions;

import javax.persistence.*;

/**
 * Сущность жесткого диска. Наследуется от сущности товара.
 * Поля хранятся в таблице "hard_disk_drives"
 * @see ProductEntity
 */
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

    @Override
    public String toString() {
        return "HddEntity {" +
                "memoryVolumeValue=" + memoryVolumeValue +
                ", memoryVolume=" + memoryVolume +
                '}';
    }
}
