package ru.cft.shiftlab.gorin.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.market.repositories.model.HddEntity;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс-Репозиторий, работающий с сущностью жесткого диска. Наследуется от Jpa-репозитория. Имплементация не нужна
 * @see JpaRepository
 * @see HddEntity
 */
@Repository
public interface HddRepository extends JpaRepository<HddEntity, Long> {
    HddEntity save(HddEntity hddEntity);

    Optional<HddEntity> findById(long id);
    List<HddEntity> findAll();
}
