package org.systems.springcloud.msvc.users.services;

import org.systems.springcloud.msvc.users.entities.dtos.UserDto;
import org.systems.springcloud.msvc.users.entities.dtos.UserStatusDto;
import org.systems.springcloud.msvc.users.entities.models.User;

public interface UserService {
    UserStatusDto getStatusUser(Long userId) throws Exception;
    UserDto saveUser(UserDto user);
    String generateToken(String username);
    void validateToken(String token);
}
