package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.dtos.ErrorMessageDTO;
import br.brazona.idp.api.core.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @Autowired
    private ErrorMessageDTO messageDTO;
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDTO resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.NOT_FOUND,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDTO globalExceptionHandler(Exception ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
