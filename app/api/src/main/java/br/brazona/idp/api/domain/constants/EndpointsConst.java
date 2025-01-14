package br.brazona.idp.api.domain.constants;

/**
* 
* Class that provides exception messages in the form of constants.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

public class EndpointsConst {

    public final static String AUTH_AUTHENTICATION = "/v1/auth/authentication";
    public final static String AUTH_AUTHORIZATION = "/v1/auth/authorization";
    public final static String AUTH_FORGOT = "/v1/auth/forgot";
    public final static String AUTH_UPDATE_PASSWORD = "/v1/auth/update/password";
    public final static String AUTH_VALIDATE_CODE = "/v1/auth/validate/code";

    /**
     *
     * Class constructor.
     *
     **/
    public EndpointsConst() {
    }
}
