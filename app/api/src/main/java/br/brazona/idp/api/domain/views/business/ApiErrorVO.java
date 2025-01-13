package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
* 
* Class View Object Message Error Default.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Component
@Getter
public class ApiErrorVO {
    private HttpStatus status;
    private String message;

    /**
     * 
     * Method Constructor of class.
     * 
     **/
    public ApiErrorVO() {
    }

    /**
     * 
     * Method constructor
     * 
     * @param status Object of the User class, with the application's authentication values.
     * @param message Object of the User class, with the application's authentication values.
     * 
     **/
    public ApiErrorVO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
