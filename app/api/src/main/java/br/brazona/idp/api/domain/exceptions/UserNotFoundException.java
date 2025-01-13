package br.brazona.idp.api.domain.exceptions;

/**
*
* Exception class UserNotFoundException
*
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public class UserNotFoundException extends RuntimeException {
    /**
     *
     * Method that returns the exception: UserNotFoundException.
     *
     * @param message Exception descriptive messages.
     *
     **/
    public UserNotFoundException(String message) {
        super(message);
    }
}
