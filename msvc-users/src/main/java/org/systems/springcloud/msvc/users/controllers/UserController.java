package org.systems.springcloud.msvc.users.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.systems.springcloud.msvc.users.entities.dtos.AuthRequest;
import org.systems.springcloud.msvc.users.entities.dtos.ResponseTokenDTO;
import org.systems.springcloud.msvc.users.entities.dtos.UserDto;
import org.systems.springcloud.msvc.users.entities.models.User;
import org.systems.springcloud.msvc.users.services.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {"http:localhost:4200","*"})
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;


    @GetMapping(path = "/validateStatus/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateStatusUser(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(service.getStatusUser(userId));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(service.saveUser(userDto), HttpStatus.OK);
    }

    @PostMapping(path = "/auth/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
            return ResponseEntity.ok(ResponseTokenDTO.builder()
                            .token(service.generateToken(authRequest.getUsername())).build());
        }
        else{
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping(path = "/auth/validateToken")
    public ResponseEntity<?> validateToken(@RequestParam String token){
        service.validateToken(token);
        return ResponseEntity.ok().build();
    }




}
