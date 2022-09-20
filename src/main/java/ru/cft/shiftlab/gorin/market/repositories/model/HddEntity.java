package ru.cft.shiftlab.gorin.market.repositories.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.market.model.enums.MemoryVolumeOptions;
import ru.cft.shiftlab.gorin.market.model.enums.ProductType;

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

    public HddEntity(String serialNumber, String manufacturer, long priceValue, int amount, ProductType productType, int memoryVolumeValue, MemoryVolumeOptions memoryVolume) {
        super(serialNumber, manufacturer, priceValue, amount, productType);
        this.memoryVolumeValue = memoryVolumeValue;
        this.memoryVolume = memoryVolume;
    }

    public HddEntity() {
    }

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
