package org.systems.springcloud.msvc.users.error;

public class SaveUserException extends RuntimeException {
    public SaveUserException(String message) {
        super(message);
    }
}
