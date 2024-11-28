package br.brazona.idp.api.domain.constants;

import org.springframework.stereotype.Component;
/**
*
* Class that provides data dictionary in the form of constants.
*
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Component
public class DictionaryConst {
    /** constant HOMEM_CISGENERO **/
    public final static String HOMEM_CISGENERO = "Homem Cisgênero";
    /** constant HOMEM_TRANSGENERO **/
    public final static String HOMEM_TRANSGENERO = "Homem Transgênero";
    /** constant HOMEM_TRANSEXUAL **/
    public final static String HOMEM_TRANSEXUAL = "Homem Transexual";
    /** constant MULHER_CISGENERO **/
    public final static String MULHER_CISGENERO = "Mulher Cisgênero";
    /** constant MULHER_TRANSGENERO **/
    public final static String MULHER_TRANSGENERO = "Mulher Transgênero";
    /** constant MULHER_TRANSEXUAL **/
    public final static String MULHER_TRANSEXUAL = "Mulher Transexual";
    /** constant NAO_BINARIO **/
    public final static String NAO_BINARIO = "Não-binário";
    /** constant AGENERO **/
    public final static String AGENERO = "Agênero";
    /** constant OWNER_SYS **/
    public final static String OWNER_SYS = "PROPRIETÁRIO DO APLICATIVO";
    /** constant ADM_SYS **/
    public final static String ADM_SYS = "ADMINISTRADOR DO APLICATIVO";
    /** constant MULHER_TRANSEXUAL **/
    public final static String USER_SYS = "OPERADOR DO APLICATIVO";
    /** constant OWNER_ORG **/
    public final static String OWNER_ORG = "PROPRIETÁRIO DA ORGANIZAÇÃO";
    /** constant ADM_ORG **/
    public final static String ADM_ORG = "ADMINISTRADOR DA ORGANIZAÇÃO";
    /** constant USER_ORG **/
    public final static String USER_ORG = "USUÁRIO DA ORGANIZAÇÃO";
    /**
     *
     * Class constructor.
     *
     **/
    public DictionaryConst() {
    }
}
