package org.system.springcloud.msvc.arithmetic.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.system.springcloud.msvc.arithmetic.clients.UserClientRest;
import org.system.springcloud.msvc.arithmetic.entities.dtos.RecordDto;
import org.system.springcloud.msvc.arithmetic.entities.dtos.UserStatusDto;
import org.system.springcloud.msvc.arithmetic.entities.dtos.ValuesDto;
import org.system.springcloud.msvc.arithmetic.entities.mapper.RecordMapper;
import org.system.springcloud.msvc.arithmetic.entities.models.Operation;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.system.springcloud.msvc.arithmetic.repositories.OperationRepository;
import org.system.springcloud.msvc.arithmetic.repositories.RecordRepository;
import org.system.springcloud.msvc.arithmetic.utils.Functions;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

class OperationServiceImplTest {

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private UserClientRest clientRest;

    @Mock
    private Functions functions;

    @Mock
    RecordMapper mapper;

    @InjectMocks
    private OperationServiceImpl service;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        functions.addition = mock(BiFunction.class);
        functions.subtraction = mock(BiFunction.class);
    }

    @Test
    void calculateAddition() throws Exception {
        given(clientRest.validateStatusUser(any())).willReturn(UserStatusDto.builder()
                .status("active").build());

        given(recordRepository.findFirstByUserIdAndOperationResponseOrderByDateDesc(anyLong(), anyBoolean())).willReturn(Optional.of(Record.builder()
                .userBalance(3)
                .build()));

        given(operationRepository.findById(anyLong())).willReturn(Optional.ofNullable(Operation.builder()
                .cost(2)
                .id(1L)
                .type("addition")
                .build()));

        given(functions.addition.apply(any(), any())).willReturn("3");


        given(recordRepository.save(Record.builder()
                .userBalance(1)
                .operationId(1L)
                .userId(1L)
                .date(LocalDateTime.now())
                .amount("3")
                .userBalance(1)
                .id(1L)
                .operationResponse(true)
                .build())).willReturn(Record.builder()
                .operationId(1L)
                .userId(1L)
                .date(LocalDateTime.now())
                .amount("3")
                .userBalance(1)
                .id(1L)
                .operationResponse(true)
                .build());

        given(mapper.recordToRecordDto(any())).willReturn(RecordDto.builder()
                .userBalance(1)
                .operationId(1L)
                .userId(1L)
                .date(LocalDateTime.now())
                .amount("3")
                .userBalance(1)
                .id(1L)
                .operationResponse(true)
                .build());

        RecordDto record = service.calculate(ValuesDto.builder()
                .valueOne(1D)
                .valueTwo(2D).build(), 1L, 1L);

        assertEquals("3", record.getAmount());
        assertEquals(true, record.getOperationResponse());
    }


    @Test
    void calculateSubstraction() throws Exception {
        given(clientRest.validateStatusUser(any())).willReturn(UserStatusDto.builder()
                .status("active").build());

        given(recordRepository.findFirstByUserIdAndOperationResponseOrderByDateDesc(anyLong(), anyBoolean())).willReturn(Optional.of(Record.builder()
                .userBalance(13)
                .build()));

        given(operationRepository.findById(anyLong())).willReturn(Optional.ofNullable(Operation.builder()
                .cost(4)
                .id(2L)
                .type("addition")
                .build()));

        given(functions.subtraction.apply(any(), any())).willReturn("-1");

        given(recordRepository.save(Record.builder()
                .userBalance(9)
                .build())).willReturn(Record.builder()
                .id(1L)
                .userBalance(3)
                .build());

        given(mapper.recordToRecordDto(any())).willReturn(RecordDto.builder()
                .userBalance(1)
                .operationId(1L)
                .userId(1L)
                .date(LocalDateTime.now())
                .amount("-1")
                .userBalance(1)
                .id(1L)
                .operationResponse(true)
                .build());

        RecordDto record = service.calculate(ValuesDto.builder()
                .valueOne(1D)
                .valueTwo(2D).build(), 1L, 1L);

        assertEquals("-1", record.getAmount());
        assertEquals(true, record.getOperationResponse());
    }


    @Test
    void calculateThrowsExceptionWhenUserIsNotActive() throws Exception {
        given(clientRest.validateStatusUser(any())).willReturn(UserStatusDto.builder()
                .status("inactive").build());

        given(recordRepository.findFirstByUserIdAndOperationResponseOrderByDateDesc(anyLong(), anyBoolean())).willReturn(Optional.of(Record.builder()
                .userBalance(3)
                .build()));

        given(operationRepository.findById(anyLong())).willReturn(null);

        assertThrows(Exception.class, () -> service.calculate(ValuesDto.builder()
                .valueOne(1D)
                .valueTwo(2D).build(), 10L, 10L));
    }


    @Test
    void calculateThrowsExceptionWhenUserBalanceIsLessOperationCost() throws Exception {
        given(clientRest.validateStatusUser(any())).willReturn(UserStatusDto.builder()
                .status("active").build());

        given(recordRepository.findFirstByUserIdAndOperationResponseOrderByDateDesc(anyLong(), anyBoolean())).willReturn(Optional.of(Record.builder()
                .userBalance(0)
                .build()));

        given(operationRepository.findById(anyLong())).willReturn(Optional.ofNullable(Operation.builder()
                .cost(1)
                .build()));

        assertThrows(Exception.class, () -> service.calculate(ValuesDto.builder()
                .valueOne(1D)
                .valueTwo(2D).build(), 10L, 10L));
    }
}