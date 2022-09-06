package com.marketlogic.survey.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> messageNoReadable(HttpMessageNotReadableException exception, HttpServletRequest request){

        String error = "Message not readable";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError sError = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(sError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException exception, HttpServletRequest request){

        String error = "Illegal argument";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError sError = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(sError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException exception, HttpServletRequest request){

        String error = "Entity not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError sError = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(sError);
    }
}
