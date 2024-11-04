package br.brazona.idp.api.domain.enuns;

import br.brazona.idp.api.domain.constants.DictionaryConst;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum RolesEnum {
    OWNER_SYS(DictionaryConst.OWNER_SYS),
    ADM_SYS(DictionaryConst.ADM_SYS),
    USER_SYS(DictionaryConst.USER_SYS),
    OWNER_ORG(DictionaryConst.OWNER_ORG),
    ADM_ORG(DictionaryConst.ADM_ORG),
    USER_ORG(DictionaryConst.USER_ORG);

    private String nome_role;

    RolesEnum(String nome) {
        this.nome_role = nome;
    }

    RolesEnum() {
    }
}
