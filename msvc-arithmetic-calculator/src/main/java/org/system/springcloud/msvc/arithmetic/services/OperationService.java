package org.system.springcloud.msvc.arithmetic.services;

import org.system.springcloud.msvc.arithmetic.entities.models.Record;

import java.util.List;

public interface OperationService {

    Record addition(List<Double> values, Long userId, Long operationId) throws Exception;
}
