package org.systems.springcloud.msvc.users.services;

import java.util.Map;

public interface JWTService {

    void validateToken(String token);
    String generateToken(String userName);

}
