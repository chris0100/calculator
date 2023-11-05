package org.systems.springcloud.msvc.users.services;

import org.systems.springcloud.msvc.users.entities.dtos.UserDto;

public interface UserService {
    UserDto getStatusUser(Long userId) throws Exception;
}
