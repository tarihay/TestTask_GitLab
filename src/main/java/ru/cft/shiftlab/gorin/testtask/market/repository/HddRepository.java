package ru.cft.shiftlab.gorin.testtask.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface HddRepository extends JpaRepository<HddEntity, Long> {
    HddEntity save(HddEntity hddEntity);

    Optional<HddEntity> findById(long id);
    List<HddEntity> findAll();
}
