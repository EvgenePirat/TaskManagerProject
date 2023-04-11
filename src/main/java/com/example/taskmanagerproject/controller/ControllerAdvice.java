package com.example.taskmanagerproject.controller;

import com.example.taskmanagerproject.exception.AccessDeniedException;
import com.example.taskmanagerproject.exception.ExceptionBody;
import com.example.taskmanagerproject.exception.ResourcesNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourcesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourcesNotFound(ResourcesNotFoundException e){
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalState(IllegalStateException e){
        return new ExceptionBody(e.getMessage());
    }


    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionBody handleAccessDenied(){
        return new ExceptionBody("Access denied!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        ExceptionBody exceptionBody = new ExceptionBody("validation fail!");
        List<FieldError> errorList = e.getFieldErrors();
        exceptionBody.setErrors(errorList.stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage)));
        return exceptionBody;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleConstraintViolation(ConstraintViolationException e){
        ExceptionBody exceptionBody = new ExceptionBody("Validation fail!");
        exceptionBody.setErrors(e.getConstraintViolations().stream().collect(Collectors.toMap(
            violation -> violation.getPropertyPath().toString(),
                ConstraintViolation::getMessage)));
        return exceptionBody;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleException(Exception e){
        return new ExceptionBody("Internal server error!");
    }
}
