/**
 * @author Brazona Tech
 **/
package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.AccessDeniedException;
import br.brazona.idp.api.domain.exceptions.BadRequestException;
import br.brazona.idp.api.domain.exceptions.NotFoundException;
import br.brazona.idp.api.domain.exceptions.UserNotFoundException;
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

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    public ExceptionHandlerController() {
        super();
    }

    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleNotfound(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        log.error(ExceptionConst.NOT_FOUND_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    public ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {

        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        log.error(ExceptionConst.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.error(ExceptionConst.BAD_REQUEST_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getName();

        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param ex excess relative to the process.
     * @return ResponseEntity<Object> excess relative to the process.
     **/
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final RuntimeException ex, final WebRequest request) {
        ApiErrorVO apiError =
                new ApiErrorVO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}