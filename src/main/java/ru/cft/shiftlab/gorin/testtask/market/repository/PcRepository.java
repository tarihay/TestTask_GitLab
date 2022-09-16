package ru.cft.shiftlab.gorin.testtask.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;

@Repository
public interface PcRepository extends JpaRepository<PcEntity, Long> {
    PcEntity save(PcEntity pcEntity);
}
