package ru.cft.shiftlab.gorin.testtask.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.testtask.market.repository.*;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.*;
import ru.cft.shiftlab.gorin.testtask.market.service.SearchProductService;

import java.util.List;
import java.util.Optional;

@Service
public class SearchProductServiceImpl implements SearchProductService {
    private final ProductRepository productRepository;
    private final HddRepository hddRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;
    private final PcRepository pcRepository;

    @Autowired
    public SearchProductServiceImpl(ProductRepository productRepository,
                                    HddRepository hddRepository,
                                    LaptopRepository laptopRepository,
                                    MonitorRepository monitorRepository,
                                    PcRepository pcRepository) {
        this.productRepository = productRepository;
        this.hddRepository = hddRepository;
        this.laptopRepository = laptopRepository;
        this.monitorRepository = monitorRepository;
        this.pcRepository = pcRepository;
    }

    @Override
    public List<ProductEntity> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<HddEntity> findHddById(long id) {
        return hddRepository.findById(id);
    }
    @Override
    public Optional<LaptopEntity> findLaptopById(long id) {
        return laptopRepository.findById(id);
    }
    @Override
    public Optional<MonitorEntity> findMonitorById(long id) {
        return monitorRepository.findById(id);
    }
    @Override
    public Optional<PcEntity> findPcById(long id) {
        return pcRepository.findById(id);
    }

    @Override
    public List<HddEntity> findAllHdds() {
        return hddRepository.findAll();
    }
    @Override
    public List<LaptopEntity> findAllLaptops() {
        return laptopRepository.findAll();
    }
    @Override
    public List<MonitorEntity> findAllMonitors() {
        return monitorRepository.findAll();
    }
    @Override
    public List<PcEntity> findAllPcs() {
        return pcRepository.findAll();
    }
}
