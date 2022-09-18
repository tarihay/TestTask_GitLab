package ru.cft.shiftlab.gorin.testtask.market.services;

import ru.cft.shiftlab.gorin.testtask.market.repositories.model.*;

import java.util.List;
import java.util.Optional;

public interface SearchProductService {
    List<ProductEntity> findById(long id);

    Optional<HddEntity> findHddById(long id);
    Optional<LaptopEntity> findLaptopById(long id);
    Optional<MonitorEntity> findMonitorById(long id);
    Optional<PcEntity> findPcById(long id);

    List<HddEntity> findAllHdds();
    List<LaptopEntity> findAllLaptops();
    List<MonitorEntity> findAllMonitors();
    List<PcEntity> findAllPcs();
}
