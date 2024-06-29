package com.fatec.contato.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> validationException(MethodArgumentNotValidException exception, HttpServletRequest request){

        ValidationErrors error = new ValidationErrors();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setError("Validation Error");
        error.setMessage(exception.getMessage());
        error.setTimeStamp(Instant.now());
        error.setPath(request.getRequestURI());

        exception.getBindingResult().getFieldErrors().forEach(e -> error.addError(e.getDefaultMessage()));

        return
        ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
