package com.marketlogic.survey.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIException> messageNoReadable(HttpMessageNotReadableException exception, HttpServletRequest request){
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIException> illegalArgument(IllegalArgumentException exception, HttpServletRequest request){
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIException> entityNotFound(EntityNotFoundException exception, HttpServletRequest request){
        return getApiException(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIException> methodArgument(MethodArgumentNotValidException exception, HttpServletRequest request){
        StringBuilder message = new StringBuilder();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) message.append(error.toString()).append(" ");
        return getApiException(HttpStatus.BAD_REQUEST, message.toString(), request.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIException> constraintViolation(ConstraintViolationException exception, HttpServletRequest request){
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<APIException> getApiException(HttpStatus status, String message, String path){
        APIException error = new APIException(ZonedDateTime.now(), status, message, path);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
