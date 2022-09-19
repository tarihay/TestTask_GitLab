package ru.cft.shiftlab.gorin.testtask.market.services;

/**
 * Сервис-Интерфейс, отвечающий за удаление товаров из базы данных. Нужна имплементация
 */
public interface DeleteProductService {
    void deleteProductById(long id);
}
