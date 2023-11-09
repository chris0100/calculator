package org.system.springcloud.msvc.arithmetic.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;

public interface RecordService {

    Page<Record> getAllRecords(Long userId, Pageable paging);
    Record removeRecordById(Long id) throws Exception;

}
