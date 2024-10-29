package br.brazona.idp.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
public class TokenDTO {
    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
