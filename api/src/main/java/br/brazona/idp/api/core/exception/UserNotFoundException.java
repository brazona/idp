package br.brazona.idp.api.core.exception;

import br.brazona.idp.api.core.constantes.ExceptionConst;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User "+ExceptionConst.NOT_FOUND);
    }
}
