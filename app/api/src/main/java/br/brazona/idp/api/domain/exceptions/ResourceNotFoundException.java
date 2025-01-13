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
public class ResourceNotFoundException extends RuntimeException {
    /**
     *
     * Method that returns the exception: ResourceNotFoundException.
     *
     * @param message Exception descriptive messages.
     *
     **/
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
