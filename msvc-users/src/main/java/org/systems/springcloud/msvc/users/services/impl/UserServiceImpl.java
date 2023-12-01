package org.systems.springcloud.msvc.users.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.systems.springcloud.msvc.users.entities.dtos.UserDto;
import org.systems.springcloud.msvc.users.entities.dtos.UserStatusDto;
import org.systems.springcloud.msvc.users.entities.models.User;
import org.systems.springcloud.msvc.users.error.GenerateTokenException;
import org.systems.springcloud.msvc.users.error.SaveUserException;
import org.systems.springcloud.msvc.users.error.UserNotFoundException;
import org.systems.springcloud.msvc.users.error.ValidateTokenException;
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
        if (user.isPresent()) {
            return mapper.map(user, UserStatusDto.class);
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public UserDto saveUser(UserDto user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userDB = mapper.map(user, User.class);
            return mapper.map(userRepository.save(userDB), UserDto.class);
        } catch (Exception e) {
            throw new SaveUserException("There is an error saving the user");
        }
    }

    @Override
    public String generateToken(String username) {
        try {
            return jwtService.generateToken(username);
        } catch (Exception e) {
            throw new GenerateTokenException("There is an error trying to generate a token");
        }
    }

    @Override
    public void validateToken(String token) {
        try {
            jwtService.validateToken(token);
        } catch (Exception e) {
            throw new ValidateTokenException("There is an error validating a token");
        }
    }
}
