package org.systems.springcloud.msvc.users.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.systems.springcloud.msvc.users.entities.dtos.UserDto;
import org.systems.springcloud.msvc.users.entities.dtos.UserStatusDto;
import org.systems.springcloud.msvc.users.entities.models.User;
import org.systems.springcloud.msvc.users.repositories.UserRepository;
import org.systems.springcloud.msvc.users.services.JWTService;
import org.systems.springcloud.msvc.users.services.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    @Override
    public UserStatusDto getStatusUser(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return mapper.map(user, UserStatusDto.class);
        }
        //change to throw error
        throw new Exception("User not found");
    }

    @Override
    public String saveUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDB = mapper.map(user, User.class);
        userRepository.save(userDB);
        return "User added to the system";
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
