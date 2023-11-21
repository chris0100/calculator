package org.system.springcloud.msvc.arithmetic.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<ApiError> handleUserNotActiveException(UserNotActiveException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<ApiError> handleUserNotEnoughBalanceException(NotEnoughBalanceException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FunctionNotAppliedException.class)
    public ResponseEntity<ApiError> handleFunctionNotAppliedException(FunctionNotAppliedException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordDataException.class)
    public ResponseEntity<ApiError> handleRecordDataException(RecordDataException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordDataNotFoundException.class)
    public ResponseEntity<ApiError> handleRecordDataNotFoundException(RecordDataNotFoundException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
