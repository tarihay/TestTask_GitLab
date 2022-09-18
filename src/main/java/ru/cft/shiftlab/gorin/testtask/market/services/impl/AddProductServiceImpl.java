package ru.cft.shiftlab.gorin.testtask.market.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.testtask.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.repositories.HddRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.LaptopRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.MonitorRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.PcRepository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.PcEntity;
import ru.cft.shiftlab.gorin.testtask.market.services.AddProductService;

@Service
public class AddProductServiceImpl implements AddProductService {

    private final HddRepository hddRepository;
    private final MonitorRepository monitorRepository;
    private final LaptopRepository laptopRepository;
    private final PcRepository pcRepository;

    @Autowired
    public AddProductServiceImpl(HddRepository hddRepository,
                                 MonitorRepository monitorRepository,
                                 LaptopRepository laptopRepository,
                                 PcRepository pcRepository) {
        this.hddRepository = hddRepository;
        this.monitorRepository = monitorRepository;
        this.laptopRepository = laptopRepository;
        this.pcRepository = pcRepository;
    }

    @Override
    public MonitorEntity saveMonitor(MonitorFeaturesDTO monitorDTO) {
        MonitorEntity monitorEntity = new MonitorEntity();
        monitorEntity.setSerialNumber(monitorDTO.getSerialNumber());
        monitorEntity.setManufacturer(monitorDTO.getManufacturer());
        monitorEntity.setPriceValue(monitorDTO.getPriceValue());
        monitorEntity.setAmount(monitorDTO.getAmount());
        monitorEntity.setProductType(monitorDTO.getProductType());

        monitorEntity.setDiagonalSize(monitorDTO.getDiagonalSize());

        return monitorRepository.save(monitorEntity);
    }

    @Override
    public LaptopEntity saveLaptop(LaptopFeaturesDTO laptopDTO) {
        LaptopEntity laptopEntity = new LaptopEntity();
        laptopEntity.setSerialNumber(laptopDTO.getSerialNumber());
        laptopEntity.setManufacturer(laptopDTO.getManufacturer());
        laptopEntity.setPriceValue(laptopDTO.getPriceValue());
        laptopEntity.setAmount(laptopDTO.getAmount());
        laptopEntity.setProductType(laptopDTO.getProductType());

        laptopEntity.setSize(laptopDTO.getSize());

        return laptopRepository.save(laptopEntity);
    }

    @Override
    public HddEntity saveHdd(HddFeaturesDTO hddDTO) {
        HddEntity hddEntity = new HddEntity();
        hddEntity.setSerialNumber(hddDTO.getSerialNumber());
        hddEntity.setManufacturer(hddDTO.getManufacturer());
        hddEntity.setPriceValue(hddDTO.getPriceValue());
        hddEntity.setAmount(hddDTO.getAmount());
        hddEntity.setProductType(hddDTO.getProductType());

        hddEntity.setMemoryVolumeValue(hddDTO.getMemoryVolumeValue());
        hddEntity.setMemoryVolume(hddDTO.getMemoryVolume());

        return hddRepository.save(hddEntity);
    }

    @Override
    public PcEntity savePc(PcFeaturesDTO pcDTO) {
        PcEntity pcEntity = new PcEntity();
        pcEntity.setSerialNumber(pcDTO.getSerialNumber());
        pcEntity.setManufacturer(pcDTO.getManufacturer());
        pcEntity.setPriceValue(pcDTO.getPriceValue());
        pcEntity.setAmount(pcDTO.getAmount());
        pcEntity.setProductType(pcDTO.getProductType());

        pcEntity.setFormFactor(pcDTO.getFormFactor());

        return pcRepository.save(pcEntity);
    }
}
