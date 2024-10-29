package br.brazona.idp.api.core.exception;

import br.brazona.idp.api.core.constantes.ExceptionConst;

import java.io.Serial;

public class UnavailableServicedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnavailableServicedException() {
        super(ExceptionConst.UNAVAILABLE_SERVICE);
    }
}
