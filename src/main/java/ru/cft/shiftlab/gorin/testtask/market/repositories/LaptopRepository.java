package ru.cft.shiftlab.gorin.testtask.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.LaptopEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {
    LaptopEntity save(LaptopEntity laptopEntity);

    Optional<LaptopEntity> findById(long id);
    List<LaptopEntity> findAll();
}
