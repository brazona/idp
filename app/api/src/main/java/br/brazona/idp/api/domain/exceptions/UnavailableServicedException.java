package br.brazona.idp.api.domain.exceptions;

/**
*
* Exception class UnavailableServicedException
*
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public class UnavailableServicedException extends RuntimeException {
    /**
     *
     * Method that returns the exception: UnavailableServicedException.
     *
     * @param message Exception descriptive messages.
     *
     **/
    public UnavailableServicedException(String message) {
        super(message);
    }
}
