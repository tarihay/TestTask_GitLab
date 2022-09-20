package ru.cft.shiftlab.gorin.market.services;

import ru.cft.shiftlab.gorin.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.PcEntity;

/**
 * Сервис-Интерфейс, отвечающий за обновление полей товаров в базе данных. Нужна имплементация
 */
public interface UpdateService {
    MonitorEntity updateMonitor(MonitorEntity monitorEntity);
    LaptopEntity updateLaptop(LaptopEntity laptopEntity);
    HddEntity updateHdd(HddEntity hddEntity);
    PcEntity updatePc(PcEntity pcEntity);
}
