package ru.cft.shiftlab.gorin.testtask.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.MonitorEntity;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс-Репозиторий, работающий с сущностью монитора. Наследуется от Jpa-репозитория. Имплементация не нужна
 * @see JpaRepository
 * @see MonitorEntity
 */
@Repository
public interface MonitorRepository extends JpaRepository<MonitorEntity, Long> {
    MonitorEntity save(MonitorEntity monitorEntity);

    Optional<MonitorEntity> findById(long id);
    List<MonitorEntity> findAll();
}
