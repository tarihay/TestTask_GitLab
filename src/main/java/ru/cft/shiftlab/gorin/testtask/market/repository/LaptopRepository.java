package ru.cft.shiftlab.gorin.testtask.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.LaptopEntity;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {
    LaptopEntity save(LaptopEntity laptopEntity);
}
