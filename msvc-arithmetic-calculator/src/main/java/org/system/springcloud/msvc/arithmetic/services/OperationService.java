package org.system.springcloud.msvc.arithmetic.services;

import org.system.springcloud.msvc.arithmetic.entities.dtos.RecordDto;
import org.system.springcloud.msvc.arithmetic.entities.dtos.ValuesDto;

public interface OperationService {

    RecordDto calculate(ValuesDto values, Long userId, Long operationId) throws Exception;

    RecordDto randomString(Long userId, Long operationId) throws Exception;
}
