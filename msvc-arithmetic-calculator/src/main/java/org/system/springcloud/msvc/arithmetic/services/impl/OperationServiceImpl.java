package org.system.springcloud.msvc.arithmetic.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.system.springcloud.msvc.arithmetic.clients.UserClientRest;
import org.system.springcloud.msvc.arithmetic.entities.dtos.RecordDto;
import org.system.springcloud.msvc.arithmetic.entities.dtos.ValuesDto;
import org.system.springcloud.msvc.arithmetic.entities.mapper.RecordMapper;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.system.springcloud.msvc.arithmetic.error.FunctionNotAppliedException;
import org.system.springcloud.msvc.arithmetic.error.NotEnoughBalanceException;
import org.system.springcloud.msvc.arithmetic.error.UserNotActiveException;
import org.system.springcloud.msvc.arithmetic.repositories.OperationRepository;
import org.system.springcloud.msvc.arithmetic.repositories.RecordRepository;
import org.system.springcloud.msvc.arithmetic.services.OperationService;
import org.system.springcloud.msvc.arithmetic.utils.Functions;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OperationServiceImpl implements OperationService {

    private final RecordRepository recordRepository;
    private final OperationRepository operationRepository;
    private final UserClientRest clientRest;

    private final Functions functions;

    private final RecordMapper mapper;


    @Override
    public RecordDto calculate(ValuesDto values, Long userId, Long operationId) throws Exception {
        return saveRecord(values, userId, operationId);
    }

    @Override
    public RecordDto randomString(Long userId, Long operationId) throws Exception {
        return saveRecord(new ValuesDto(), userId, operationId);
    }


    private RecordDto saveRecord(ValuesDto values, Long userId, Long operationId) throws Exception {
        String status = clientRest.validateStatusUser(userId).getStatus();
        log.info("userStatus with id {} is {}", userId, status);

        if (status.equals("active")){
            Optional<Record> lastRecordUser = recordRepository.findFirstByUserIdAndOperationResponseOrderByDateDesc(userId, true);
            int lastUserBalance = getLastUserBalance(lastRecordUser);
            int operationCost = operationRepository.findById(operationId).orElseThrow(Exception::new).getCost();

            if (lastUserBalance < operationCost){
                throw new NotEnoughBalanceException("There is not enough user balance to apply the operation");
            }
            else {
                lastUserBalance -= operationCost;

                String amount = chooseFunction(values, operationCost);

                Record record = Record.builder()
                        .date(LocalDateTime.now())
                        .amount(amount)
                        .operationResponse(true)
                        .userBalance(lastUserBalance)
                        .userId(userId)
                        .operationId(operationId)
                        .build();

                recordRepository.save(record);
                return mapper.recordToRecordDto(record);
            }
        } else {
            throw new UserNotActiveException("the user is not active");
        }
    }


    private String chooseFunction(ValuesDto values, int operationCost){
        try{
            return switch (operationCost) {
                case 2 -> functions.addition.apply(values.getValueOne(), values.getValueTwo());
                case 4 -> functions.subtraction.apply(values.getValueOne(), values.getValueTwo());
                case 6 -> functions.multiplication.apply(values.getValueOne(), values.getValueTwo());
                case 8 -> functions.division.apply(values.getValueOne(), values.getValueTwo());
                case 10 -> functions.squareRoot.apply(values.getValueOne());
                case 12 -> functions.randomString.get();
                default -> "0";
            };
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new FunctionNotAppliedException("The function cannot be applied, please try later");
        }
    }


    private int getLastUserBalance(Optional<Record> lastRecordUser) {
        return lastRecordUser.isEmpty() ? 20 : lastRecordUser.get().getUserBalance();
    }
}
