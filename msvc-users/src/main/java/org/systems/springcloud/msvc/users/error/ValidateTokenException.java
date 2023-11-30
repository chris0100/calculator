package org.systems.springcloud.msvc.users.error;

public class ValidateTokenException extends RuntimeException {
    public ValidateTokenException(String message) {
        super(message);
    }
}
