package org.system.springcloud.msvc.arithmetic.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.system.springcloud.msvc.arithmetic.entities.models.Record;
import org.system.springcloud.msvc.arithmetic.services.RecordService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecordController {

    private final RecordService service;


    @GetMapping(path = "/record", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getAllRecords(@RequestParam(name = "userId") Long userId,
                                                             @RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam(defaultValue = "desc") String sort) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Record> allRecords = service.getAllRecords(userId, sort, paging);

            Map<String, Object> response = new HashMap<>();
            response.put("records", allRecords);
            response.put("currentPage", allRecords.getNumber());
            response.put("totalItems", allRecords.getTotalElements());
            response.put("totalPages", allRecords.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("not found exception ");
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/record/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getAllRecordsByFilter(@RequestParam(name = "userId") Long userId,
                                                                     @RequestParam int page,
                                                                     @RequestParam int size,
                                                                     @RequestParam String filter) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Record> allRecords = service.getAllRecordsByFilter(userId, filter, paging);

            Map<String, Object> response = new HashMap<>();
            response.put("records", allRecords);
            response.put("currentPage", allRecords.getNumber());
            response.put("totalItems", allRecords.getTotalElements());
            response.put("totalPages", allRecords.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("not found exception ");
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> removeRecordById(@RequestParam(name = "recordId") Long recordId) {
        try {
            return ResponseEntity.ok(service.removeRecordById(recordId));
        } catch (Exception e) {
            log.error("not found exception ");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
