package org.systems.springcloud.msvc.users.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.systems.springcloud.msvc.users.services.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;


    @GetMapping(path = "/validateStatus/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateStatusUser(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(service.getStatusUser(userId));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
