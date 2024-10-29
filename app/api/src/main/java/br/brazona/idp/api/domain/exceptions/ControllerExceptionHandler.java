package br.brazona.idp.api.domain.exceptions;

import br.brazona.idp.api.domain.dto.business.ErrorMessageDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDTO resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.NOT_FOUND,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDTO userNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.NOT_FOUND,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }
    @ExceptionHandler(UnavailableServicedException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMessageDTO unavailableServicedException(UnavailableServicedException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.SERVICE_UNAVAILABLE,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO malformedJwtException(MalformedJwtException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessageDTO expiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.FORBIDDEN,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }
    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO unsupportedJwtException(UnsupportedJwtException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST,
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST,
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
