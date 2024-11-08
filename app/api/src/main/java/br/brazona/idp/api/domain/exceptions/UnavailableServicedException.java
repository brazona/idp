package br.brazona.idp.api.domain.exceptions;

import br.brazona.idp.api.domain.constants.ExceptionConst;

import java.io.Serial;

public class UnavailableServicedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnavailableServicedException() {
        super(ExceptionConst.UNAVAILABLE_SERVICE_ERROR);
    }
}
