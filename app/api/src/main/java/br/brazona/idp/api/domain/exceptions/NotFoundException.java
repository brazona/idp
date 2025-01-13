package br.brazona.idp.api.domain.exceptions;

/**
*
* Exception class NotFoundException
*
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public class NotFoundException extends RuntimeException {
    /**
     *
     * Method that returns the exception: NotFoundException.
     *
     * @param message Exception descriptive messages.
     *
     **/
    public NotFoundException(String message) {
        super(message);
    }
}
