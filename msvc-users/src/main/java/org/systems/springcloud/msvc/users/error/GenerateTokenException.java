package org.systems.springcloud.msvc.users.error;

public class GenerateTokenException extends RuntimeException {
    public GenerateTokenException(String message) {
        super(message);
    }
}
