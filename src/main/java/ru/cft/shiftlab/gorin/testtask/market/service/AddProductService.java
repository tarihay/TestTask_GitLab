package ru.cft.shiftlab.gorin.testtask.market.service;


import ru.cft.shiftlab.gorin.testtask.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;

import java.util.Optional;

public interface AddProductService {
    MonitorEntity saveMonitor(MonitorFeaturesDTO monitorDTO);
    LaptopEntity saveLaptop(LaptopFeaturesDTO laptopDTO);
    HddEntity saveHdd(HddFeaturesDTO hddDTO);
    PcEntity savePc(PcFeaturesDTO pcDTO);
}
