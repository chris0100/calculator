package org.system.springcloud.msvc.arithmetic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.system.springcloud.msvc.arithmetic.entities.models.Operation;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.system.springcloud.msvc.arithmetic.repositories.OperationRepository;
import org.system.springcloud.msvc.arithmetic.repositories.RecordRepository;
import org.system.springcloud.msvc.arithmetic.services.RecordService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final OperationRepository operationRepository;



    @Override
    public Page<Record> getAllRecords(Long userId, Pageable paging) {
        List<Record> recordList = recordRepository.findAllByUserId(userId, paging)
                .stream()
                .filter(Record::getOperationResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(recordList);
    }

    @Override
    public Record removeRecordById(Long id) throws Exception {
        Optional<Record> recordDb = recordRepository.findById(id);
        if (recordDb.isPresent()){
            Record record = recordDb.get();
            record.setOperationResponse(false);

            Optional<Operation> operationUserRemoved = operationRepository.findById(record.getOperationId());
            Integer costOperation = operationUserRemoved.get().getCost();
            Record recordRemoved = recordRepository.save(record);


            //modify lastRecordUser
            Optional<Record> lastRecordUser = recordRepository.findFirstByUserIdAndOperationResponseOrderByDateDesc(record.getUserId(),true);
            if (lastRecordUser.isPresent()){
                Record lastRecordInList = lastRecordUser.get();
                lastRecordInList.setUserBalance(lastRecordInList.getUserBalance() + costOperation);
                recordRepository.save(lastRecordInList);
            }
            return recordRemoved;
        }
        throw new Exception();
    }

    @Override
    public Page<Record> getAllRecordsByFilter(Long userId, String filter, Pageable paging) {
        //look for the operationId
        Long idOperation = operationRepository.findByTypeContainingIgnoreCase(filter).getId();

        List<Record> recordList = recordRepository.findAllByUserIdAndOperationId(userId, idOperation, paging)
                .stream()
                .filter(Record::getOperationResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(recordList);
    }


}
