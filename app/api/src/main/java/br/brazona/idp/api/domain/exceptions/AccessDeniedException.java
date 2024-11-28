package br.brazona.idp.api.domain.exceptions;

/**
* 
* Exception class AccessDeniedException
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
public class AccessDeniedException extends RuntimeException {
    /**
    * 
    * Method that returns the exception: AccessDeniedException.
    * 
    * @param message Exception descriptive messages.
    *
    **/   
    public AccessDeniedException(String message) {
        super(message);
    }

}
