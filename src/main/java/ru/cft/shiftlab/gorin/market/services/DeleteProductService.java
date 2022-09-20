package ru.cft.shiftlab.gorin.market.services;

import ru.cft.shiftlab.gorin.market.repositories.model.ProductEntity;

/**
 * Сервис-Интерфейс, отвечающий за удаление товаров из базы данных. Нужна имплементация
 */
public interface DeleteProductService {
    void deleteProductById(long id);
}
