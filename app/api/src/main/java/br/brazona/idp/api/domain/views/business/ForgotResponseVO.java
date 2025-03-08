package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms ForgotResponseVO data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Getter
@Component
public class ForgotResponseVO {
    private Boolean is_update;
    private String message;

    public ForgotResponseVO() {
    }

    public ForgotResponseVO(Boolean is_update, String message) {
        this.is_update = is_update;
        this.message = message;
    }
}
