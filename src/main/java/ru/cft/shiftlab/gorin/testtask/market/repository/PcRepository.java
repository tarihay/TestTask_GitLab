package ru.cft.shiftlab.gorin.testtask.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.PcEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PcRepository extends JpaRepository<PcEntity, Long> {
    PcEntity save(PcEntity pcEntity);

    Optional<PcEntity> findById(long id);
}
