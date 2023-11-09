package org.system.springcloud.msvc.arithmetic.services;

import org.system.springcloud.msvc.arithmetic.entities.dtos.ValuesDto;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;

public interface OperationService {

    Record calculate(ValuesDto values, Long userId, Long operationId) throws Exception;

    Record randomString(Long userId, Long operationId) throws Exception;
}
