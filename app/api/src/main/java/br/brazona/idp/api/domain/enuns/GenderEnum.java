package br.brazona.idp.api.domain.enuns;

import br.brazona.idp.api.domain.constants.DictionaryConst;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public enum GenderEnum {
    HOMEM_CISGENERO(DictionaryConst.HOMEM_CISGENERO),
    HOMEM_TRANSGENERO(DictionaryConst.HOMEM_TRANSGENERO),
    HOMEM_TRANSEXUAL(DictionaryConst.HOMEM_TRANSEXUAL),
    MULHER_CISGENERO(DictionaryConst.MULHER_CISGENERO),
    MULHER_TRANSGENERO(DictionaryConst.MULHER_TRANSGENERO),
    MULHER_TRANSEXUAL(DictionaryConst.MULHER_TRANSEXUAL),
    NAO_BINARIO(DictionaryConst.NAO_BINARIO),
    AGENERO(DictionaryConst.AGENERO);

    private final String nome_genero;

    GenderEnum(String genero) {
        this.nome_genero = genero;
    }
}
