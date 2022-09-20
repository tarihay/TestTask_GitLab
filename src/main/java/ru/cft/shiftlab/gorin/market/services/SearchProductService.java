package ru.cft.shiftlab.gorin.market.services;

import ru.cft.shiftlab.gorin.market.model.enums.ProductType;
import ru.cft.shiftlab.gorin.market.repositories.model.*;
import ru.cft.shiftlab.gorin.market.repositories.model.*;

import java.util.List;
import java.util.Optional;

/**
 * Сервис-Интерфейс, отвечающий за поиск товаров в базе данных. Нужна имплементация
 */
public interface SearchProductService {
    List<ProductEntity> findById(long id);

    Optional<HddEntity> findHddById(long id);
    Optional<LaptopEntity> findLaptopById(long id);
    Optional<MonitorEntity> findMonitorById(long id);
    Optional<PcEntity> findPcById(long id);

    List<ProductEntity> findAllProductsByType(ProductType productType);
}
