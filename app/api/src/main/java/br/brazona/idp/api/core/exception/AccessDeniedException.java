package br.brazona.idp.api.core.exception;

import br.brazona.idp.api.core.constantes.ExceptionConst;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccessDeniedException() {
        super(ExceptionConst.ACCESS_DENIED);
    }
}
