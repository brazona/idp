package br.brazona.idp.api.domain.exceptions;

public class UnavailableServicedException extends RuntimeException {
    public UnavailableServicedException(String message) {
        super(message);
    }
}
