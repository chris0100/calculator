package org.system.springcloud.msvc.arithmetic.services;

import org.springframework.data.domain.Page;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;

import java.util.List;

public interface RecordService {

    List<Record> getAllRecords(Long userId);
    Record removeRecordById(Long id) throws Exception;

}
