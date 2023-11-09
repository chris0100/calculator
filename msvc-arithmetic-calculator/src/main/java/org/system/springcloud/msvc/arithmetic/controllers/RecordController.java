package org.system.springcloud.msvc.arithmetic.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.system.springcloud.msvc.arithmetic.services.OperationService;
import org.system.springcloud.msvc.arithmetic.services.RecordService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecordController {

    private final RecordService service;



    @GetMapping(path = "/record", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllRecords(@RequestParam(name = "userId") Long userId){
        try {
            return ResponseEntity.ok(service.getAllRecords(userId));
        } catch (Exception e) {
            log.error("not found exception ");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> removeRecordById(@RequestParam(name = "recordId") Long recordId){
        try {
            return ResponseEntity.ok(service.removeRecordById(recordId));
        } catch (Exception e) {
            log.error("not found exception ");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
