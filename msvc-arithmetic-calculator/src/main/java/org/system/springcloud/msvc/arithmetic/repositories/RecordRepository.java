package org.system.springcloud.msvc.arithmetic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Optional<Record> findFirstByUserIdAndOperationResponseOrderByDateDesc(Long userId, Boolean operationResponse);

    Page<Record> findAllByUserId(Long userId, Pageable pageable);
}
