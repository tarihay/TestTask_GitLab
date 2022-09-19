package ru.cft.shiftlab.gorin.testtask.market.services;


import ru.cft.shiftlab.gorin.testtask.market.model.HddFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.LaptopFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.MonitorFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.model.PcFeaturesDTO;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.LaptopEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.MonitorEntity;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.PcEntity;

/**
 * Сервис-Интерфейс, отвечающий за добавление товаров в базу данных. Нужна имплементация
 */
public interface AddProductService {
    MonitorEntity saveMonitor(MonitorFeaturesDTO monitorDTO);
    LaptopEntity saveLaptop(LaptopFeaturesDTO laptopDTO);
    HddEntity saveHdd(HddFeaturesDTO hddDTO);
    PcEntity savePc(PcFeaturesDTO pcDTO);
}
