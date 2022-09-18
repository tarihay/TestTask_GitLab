package ru.cft.shiftlab.gorin.testtask.market.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.testtask.market.repositories.HddRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.LaptopRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.MonitorRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.PcRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.PcEntity;
import ru.cft.shiftlab.gorin.testtask.market.services.UpdateService;

@Service
public class UpdateServiceImpl implements UpdateService {
    private final HddRepository hddRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;
    private final PcRepository pcRepository;

    @Autowired
    public UpdateServiceImpl(HddRepository hddRepository, LaptopRepository laptopRepository, MonitorRepository monitorRepository, PcRepository pcRepository) {
        this.hddRepository = hddRepository;
        this.laptopRepository = laptopRepository;
        this.monitorRepository = monitorRepository;
        this.pcRepository = pcRepository;
    }


    @Override
    public MonitorEntity updateMonitor(MonitorEntity monitorEntity) {
        return monitorRepository.save(monitorEntity);
    }

    @Override
    public LaptopEntity updateLaptop(LaptopEntity laptopEntity) {
        return laptopRepository.save(laptopEntity);
    }

    @Override
    public HddEntity updateHdd(HddEntity hddEntity) {
        return hddRepository.save(hddEntity);
    }

    @Override
    public PcEntity updatePc(PcEntity pcEntity) {
        return pcRepository.save(pcEntity);
    }
}
