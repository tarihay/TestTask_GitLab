package ru.cft.shiftlab.gorin.testtask.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repository.model.MonitorEntity;

@Repository
public interface MonitorRepository extends JpaRepository<MonitorEntity, Long> {
    MonitorEntity save(MonitorEntity monitorEntity);
}
