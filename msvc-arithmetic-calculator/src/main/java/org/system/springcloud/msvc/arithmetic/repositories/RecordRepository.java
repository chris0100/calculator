package org.system.springcloud.msvc.arithmetic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Optional<Record> findFirstByUserIdAndOperationResponseOrderByDateDesc(Long userId, Boolean operationResponse);

    List<Record> findAllByUserId(Long userId);
}
