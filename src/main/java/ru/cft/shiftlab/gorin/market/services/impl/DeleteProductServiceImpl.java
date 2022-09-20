package ru.cft.shiftlab.gorin.market.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.market.repositories.ProductRepository;
import ru.cft.shiftlab.gorin.market.repositories.model.ProductEntity;
import ru.cft.shiftlab.gorin.market.services.DeleteProductService;

/**
 * Имплементация Сервиса-Интерфейса по удалению товаров
 * @see DeleteProductService
 */
@Service
public class DeleteProductServiceImpl implements DeleteProductService {
    private final ProductRepository productRepository;

    @Autowired
    public DeleteProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}
