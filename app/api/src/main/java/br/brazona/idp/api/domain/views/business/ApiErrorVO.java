package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApiErrorVO {
    private HttpStatus status;
    private String message;

    public ApiErrorVO() {
    }

    public ApiErrorVO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
