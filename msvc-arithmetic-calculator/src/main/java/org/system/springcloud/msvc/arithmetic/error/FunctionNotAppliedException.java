package org.system.springcloud.msvc.arithmetic.error;

public class FunctionNotAppliedException extends RuntimeException {
    public FunctionNotAppliedException(String message) {
        super(message);
    }
}
