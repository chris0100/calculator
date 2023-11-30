package org.systems.springcloud.msvc.users.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveUserException.class)
    public ResponseEntity<ApiError> saveUserException(SaveUserException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenerateTokenException.class)
    public ResponseEntity<ApiError> generateTokenException(GenerateTokenException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidateTokenException.class)
    public ResponseEntity<ApiError> validateTokenException(ValidateTokenException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ApiError> userNotAuthenticatedException(UserNotAuthenticatedException e, HttpServletRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
