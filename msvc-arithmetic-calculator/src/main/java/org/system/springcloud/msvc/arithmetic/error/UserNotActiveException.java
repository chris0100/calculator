package org.system.springcloud.msvc.arithmetic.error;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(String message){
        super(message);
    }
}
