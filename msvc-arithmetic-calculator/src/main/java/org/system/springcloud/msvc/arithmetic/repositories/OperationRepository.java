package org.system.springcloud.msvc.arithmetic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.springcloud.msvc.arithmetic.entities.models.Operation;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
