package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.*;
import br.brazona.idp.api.domain.views.business.ApiErrorVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
* 
* Class that controls error responses to the web interface through the api.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    /**
     *
     * Class constructor.
     *
     **/
    public ExceptionHandlerController() {
        super();
    }
     /**
     * 
     * Method that returns not found, http status 404 when exception is UserNotFoundException.class
     * 
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     *
     **/ 
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleNotfound(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        log.error(ExceptionConst.NOT_FOUND_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    /**
     * Method that returns Wrong format, http status 400 when exception is ConstraintViolationException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * 
     * Method that returns Wrong format, http status 400 when exception is DataIntegrityViolationException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * 
     * Method that returns Wrong format, http status 400 when exception is InvalidDataAccessApiUsageException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    public ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    /**
     * 
     * Method that returns Wrong format, http status 400 when exception is IllegalArgumentException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {

        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        log.error(ExceptionConst.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    /**
     * 
     * Method that returns not found, http status 404 when exception is UserNotFoundException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * 
     * Method that returns not found, http status 404 when exception is UserNotFoundException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * 
     * Method that returns not found, http status 404 when exception is UserNotFoundException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        log.error(ExceptionConst.ACCESS_DENIED, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
    /**
     * 
     * Method that returns not found, http status 404 when exception is UserNotFoundException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     * 
     **/
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        log.error(ExceptionConst.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     *
     * Method that returns not found, http status 404 when exception is UserNotFoundException.class
     *
     * @param ex Exception class.
     * @param request request class.
     * @return a response with http standard
     *
     **/
    @ExceptionHandler({EmailNotSendException.class})
    public ResponseEntity<Object> handleEmailNotSendException(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.SERVICE_UNAVAILABLE, ex.getLocalizedMessage());
        log.error(ExceptionConst.SERVICE_UNAVAILABLE, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

}