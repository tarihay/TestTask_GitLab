package ru.cft.shiftlab.gorin.testtask.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.shiftlab.gorin.testtask.market.repository.ProductRepository;
import ru.cft.shiftlab.gorin.testtask.market.service.DeleteProductService;

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
