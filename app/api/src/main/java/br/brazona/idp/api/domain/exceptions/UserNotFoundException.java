package br.brazona.idp.api.domain.exceptions;

import br.brazona.idp.api.domain.constants.ExceptionConst;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User "+ExceptionConst.NOT_FOUND);
    }
}
