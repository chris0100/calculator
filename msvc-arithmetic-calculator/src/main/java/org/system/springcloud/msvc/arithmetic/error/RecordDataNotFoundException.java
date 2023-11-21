package org.system.springcloud.msvc.arithmetic.error;

public class RecordDataNotFoundException extends RuntimeException {
    public RecordDataNotFoundException(String message) {
        super(message);
    }
}
