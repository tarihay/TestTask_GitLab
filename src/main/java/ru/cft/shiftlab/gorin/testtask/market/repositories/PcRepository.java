package ru.cft.shiftlab.gorin.testtask.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.shiftlab.gorin.testtask.market.repositories.model.PcEntity;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс-Репозиторий, работающий с сущностью ПК. Наследуется от Jpa-репозитория. Имплементация не нужна
 * @see JpaRepository
 * @see PcEntity
 */
@Repository
public interface PcRepository extends JpaRepository<PcEntity, Long> {
    PcEntity save(PcEntity pcEntity);

    Optional<PcEntity> findById(long id);
    List<PcEntity> findAll();
}
