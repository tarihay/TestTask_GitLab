package ru.cft.shiftlab.gorin.testtask.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.HddEntity;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonitorRepository extends JpaRepository<MonitorEntity, Long> {
    MonitorEntity save(MonitorEntity monitorEntity);

    Optional<MonitorEntity> findById(long id);
    List<MonitorEntity> findAll();
}
