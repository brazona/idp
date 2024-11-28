package br.brazona.idp.api.domain.enuns;

import br.brazona.idp.api.domain.constants.DictionaryConst;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
* 
* Data dictionary for access profiles.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Getter
public enum RolesEnum {

    /** Access profile: OWNER_SYS **/ OWNER_SYS(DictionaryConst.OWNER_SYS),
    /** Access profile: ADM_SYS **/   ADM_SYS(DictionaryConst.ADM_SYS),
    /** Access profile: USER_SYS **/  USER_SYS(DictionaryConst.USER_SYS),
    /** Access profile: OWNER_ORG **/ OWNER_ORG(DictionaryConst.OWNER_ORG),
    /** Access profile: ADM_ORG **/   ADM_ORG(DictionaryConst.ADM_ORG),
    /** Access profile: USER_ORG **/  USER_ORG(DictionaryConst.USER_ORG);

    private String nome_role;
    /**
     * 
     * Method that returns role name.
     * 
     * @param nome Name of the role, according to the constant DictionaryConst.class.
     * 
     **/
    RolesEnum(String nome) {
        this.nome_role = nome;
    }
    /**
     * 
     * Constructor Data dictionary for access profiles.
     * 
     **/
    RolesEnum() {
    }
}
