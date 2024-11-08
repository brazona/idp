package br.brazona.idp.api.domain.exceptions;

import br.brazona.idp.api.domain.constants.ExceptionConst;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccessDeniedException() {
        super(ExceptionConst.ACCESS_DENIED);
    }
}
