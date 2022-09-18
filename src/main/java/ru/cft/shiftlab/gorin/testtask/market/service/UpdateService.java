package ru.cft.shiftlab.gorin.testtask.market.service;

import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;

public interface UpdateService {
    MonitorEntity updateMonitor(MonitorEntity monitorEntity);
    LaptopEntity updateLaptop(LaptopEntity laptopEntity);
    HddEntity updateHdd(HddEntity hddEntity);
    PcEntity updatePc(PcEntity pcEntity);
}
