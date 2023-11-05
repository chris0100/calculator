package org.system.springcloud.msvc.arithmetic.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.system.springcloud.msvc.arithmetic.clients.UserClientRest;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.system.springcloud.msvc.arithmetic.repositories.OperationRepository;
import org.system.springcloud.msvc.arithmetic.repositories.RecordRepository;
import org.system.springcloud.msvc.arithmetic.services.OperationService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OperationServiceImpl implements OperationService {

    private final RecordRepository recordRepository;
    private final OperationRepository operationRepository;
    private final UserClientRest clientRest;


    @Override
    public Record addition(List<Double> values, Long userId, Long operationId) throws Exception {
        String status = clientRest.validateStatusUser(userId).getStatus();
        log.info("userStatus with id {} is {}", userId, status);

        if (status.equals("active")){
            Optional<Record> lastRecordUser = recordRepository.findFirstByUserIdOrderByDateDesc(userId);
            int lastUserBalance = getLastUserBalance(lastRecordUser);
            double operationCost = operationRepository.findById(operationId).orElseThrow(Exception::new).getCost();

            if (lastUserBalance < operationCost){
                throw new Exception("There is not enough user balance to apply the operation");
            }
            else {
                lastUserBalance -= operationCost;

                double amount = values.stream()
                        .filter(value -> !value.isNaN())
                        .mapToDouble(value -> value)
                        .sum();

                Record record = Record.builder()
                        .date(new Date())
                        .amount(amount)
                        .operationResponse(true)
                        .userBalance(lastUserBalance)
                        .userId(userId)
                        .operationId(operationId)
                        .build();

                recordRepository.save(record);
                return record;
            }
        } else {
            throw new Exception("User is not active");
        }



    }




    private static int getLastUserBalance(Optional<Record> lastRecordUser) {
        return lastRecordUser.isEmpty() ? 20 : lastRecordUser.get().getUserBalance();
    }
}
