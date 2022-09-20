package ru.cft.shiftlab.gorin.market.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.market.controllers.AddProductController;
import ru.cft.shiftlab.gorin.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.market.repositories.HddRepository;
import ru.cft.shiftlab.gorin.market.repositories.LaptopRepository;
import ru.cft.shiftlab.gorin.market.repositories.MonitorRepository;
import ru.cft.shiftlab.gorin.market.repositories.PcRepository;
import ru.cft.shiftlab.gorin.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.market.repositories.model.PcEntity;
import ru.cft.shiftlab.gorin.market.services.AddProductService;

/**
 * Имплементация Сервиса-Интерфейса по добавлению товаров
 * @see AddProductService
 */
@Service
public class AddProductServiceImpl implements AddProductService {
    private static final Logger logger = LogManager.getLogger(AddProductServiceImpl.class);

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

    /**
     * Метод парсит пришедшее DTO монитора в сущность и отправляет на сохранение репозиторию
     * @param monitorDTO поступившее в post-запросе DTO монитора
     * @return возвращает контроллеру сохраненную сущность
     * @see AddProductController
     * @see MonitorEntity
     * @see MonitorFeaturesDTO
     */
    @Override
    public MonitorEntity saveMonitor(MonitorFeaturesDTO monitorDTO) {
        MonitorEntity monitorEntity = new MonitorEntity();
        monitorEntity.setSerialNumber(monitorDTO.getSerialNumber());
        monitorEntity.setManufacturer(monitorDTO.getManufacturer());
        monitorEntity.setPriceValue(monitorDTO.getPriceValue());
        monitorEntity.setAmount(monitorDTO.getAmount());
        monitorEntity.setProductType(monitorDTO.getProductType());

        monitorEntity.setDiagonalSize(monitorDTO.getDiagonalSize());

        logger.info("MonitorDTO " + monitorDTO + " successfully unparsed");

        return monitorRepository.save(monitorEntity);
    }

    /**
     * Метод парсит пришедшее DTO ноутбука в сущность и отправляет на сохранение репозиторию
     * @param laptopDTO поступившее в post-запросе DTO ноутбука
     * @return возвращает контроллеру сохраненную сущность
     * @see AddProductController
     * @see LaptopEntity
     * @see LaptopFeaturesDTO
     */
    @Override
    public LaptopEntity saveLaptop(LaptopFeaturesDTO laptopDTO) {
        LaptopEntity laptopEntity = new LaptopEntity();
        laptopEntity.setSerialNumber(laptopDTO.getSerialNumber());
        laptopEntity.setManufacturer(laptopDTO.getManufacturer());
        laptopEntity.setPriceValue(laptopDTO.getPriceValue());
        laptopEntity.setAmount(laptopDTO.getAmount());
        laptopEntity.setProductType(laptopDTO.getProductType());

        laptopEntity.setSize(laptopDTO.getSize());

        logger.info("LaptopDTO " + laptopDTO + " successfully unparsed");

        return laptopRepository.save(laptopEntity);
    }

    /**
     * Метод парсит пришедшее DTO жесткого диска в сущность и отправляет на сохранение репозиторию
     * @param hddDTO поступившее в post-запросе DTO жесткого диска
     * @return возвращает контроллеру сохраненную сущность
     * @see AddProductController
     * @see HddEntity
     * @see HddFeaturesDTO
     */
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

        logger.info("HddDTO " + hddDTO + " successfully unparsed");

        return hddRepository.save(hddEntity);
    }

    /**
     * Метод парсит пришедшее DTO ПК в сущность и отправляет на сохранение репозиторию
     * @param pcDTO поступившее в post-запросе DTO ПК
     * @return возвращает контроллеру сохраненную сущность
     * @see AddProductController
     * @see PcEntity
     * @see PcFeaturesDTO
     */
    @Override
    public PcEntity savePc(PcFeaturesDTO pcDTO) {
        PcEntity pcEntity = new PcEntity();
        pcEntity.setSerialNumber(pcDTO.getSerialNumber());
        pcEntity.setManufacturer(pcDTO.getManufacturer());
        pcEntity.setPriceValue(pcDTO.getPriceValue());
        pcEntity.setAmount(pcDTO.getAmount());
        pcEntity.setProductType(pcDTO.getProductType());

        pcEntity.setFormFactor(pcDTO.getFormFactor());

        logger.info("PcDTO " + pcDTO + " successfully unparsed");

        return pcRepository.save(pcEntity);
    }
}
