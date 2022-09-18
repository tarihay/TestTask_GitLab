package ru.cft.shiftlab.gorin.testtask.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.testtask.market.repository.HddRepository;
import ru.cft.shiftlab.gorin.testtask.market.repository.LaptopRepository;
import ru.cft.shiftlab.gorin.testtask.market.repository.MonitorRepository;
import ru.cft.shiftlab.gorin.testtask.market.repository.PcRepository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;
import ru.cft.shiftlab.gorin.testtask.market.service.UpdateService;

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
