package org.system.springcloud.msvc.arithmetic.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.system.springcloud.msvc.arithmetic.entities.dtos.ValuesDto;
import org.system.springcloud.msvc.arithmetic.services.OperationService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/arithmetic")
public class CalculatorController {

    private final OperationService service;



    @PostMapping(path = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> calculate(@RequestBody ValuesDto values,
                                       @RequestParam(name = "userId") Long userId,
                                       @RequestParam(name = "operationId") Long operationId) throws Exception {
            return ResponseEntity.ok(service.calculate(values, userId, operationId));
    }

    @GetMapping(path = "/randomString", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> randomString(@RequestParam(name = "userId") Long userId,
                                                  @RequestParam(name = "operationId") Long operationId) throws Exception {
            return ResponseEntity.ok(service.randomString(userId, operationId));
    }
}
