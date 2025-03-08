package br.brazona.idp.api.domain.exceptions;

/**
 *
 * Exception class BadRequestException
 *
 * @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public class BadRequestException extends RuntimeException {
    /**
     *
     * Method that returns the exception: BadRequestException.
     *
     * @param message Exception descriptive messages.
     *
     **/
    public BadRequestException(String message) {
        super(message);
    }
}
