package org.systems.springcloud.msvc.users.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.systems.springcloud.msvc.users.entities.dtos.UserDto;
import org.systems.springcloud.msvc.users.entities.models.User;
import org.systems.springcloud.msvc.users.repositories.UserRepository;
import org.systems.springcloud.msvc.users.services.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public UserDto getStatusUser(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return mapper.map(user, UserDto.class);
        }
        //change to throw error
        throw new Exception("User not found");
    }
}
