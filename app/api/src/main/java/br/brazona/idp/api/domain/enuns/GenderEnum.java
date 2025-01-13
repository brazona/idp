package br.brazona.idp.api.domain.enuns;

import br.brazona.idp.api.domain.constants.DictionaryConst;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Getter
public enum GenderEnum {
    /** enum HOMEM_CISGENERO **/
    HOMEM_CISGENERO(DictionaryConst.HOMEM_CISGENERO),
    /** enum HOMEM_TRANSGENERO **/
    HOMEM_TRANSGENERO(DictionaryConst.HOMEM_TRANSGENERO),
    /** first day of the week **/
    HOMEM_TRANSEXUAL(DictionaryConst.HOMEM_TRANSEXUAL),
    /** first day of the week **/
    MULHER_CISGENERO(DictionaryConst.MULHER_CISGENERO),
    /** first day of the week **/
    MULHER_TRANSGENERO(DictionaryConst.MULHER_TRANSGENERO),
    /** first day of the week **/
    MULHER_TRANSEXUAL(DictionaryConst.MULHER_TRANSEXUAL),
    /** first day of the week **/
    NAO_BINARIO(DictionaryConst.NAO_BINARIO),
    /** first day of the week **/
    AGENERO(DictionaryConst.AGENERO);

    private String nome_genero;

    /**
     *
     * Method that provides the object with authentication data.
     *
     **/
    GenderEnum() {}

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param genero Instance of the session class, with value referring to the user.
     * 
     **/
    GenderEnum(String genero) {

        this.nome_genero = genero;
    }



}
