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

public class ExceptionConst {
    /** constant ACCESS_DENIED **/
    public final static String ACCESS_DENIED = "access denied";
    /** constant ACCESS_DENIED **/
    public final static String ACCESS_DENIED_PASSWORD = "access denied, atualize o password";
    /** constant NOT_FOUND **/
    public final static String NOT_FOUND = "not found";
    /** constant NOT_FOUND_INFO **/
    public final static String NOT_FOUND_INFO = "{} not found: {}";
    /** constant NOT_FOUND_ERROR **/
    public final static String NOT_FOUND_ERROR = "{} not found";
    /** constant BAD_REQUEST_ERROR **/
    public final static String BAD_REQUEST_ERROR = "{} bad format";
    /** constant FOUND_INFO **/
    public final static String FOUND_INFO = "{} found: {}";
    /** constant FOUND_DEBUG **/
    public final static String FOUND_DEBUG = "{} found: {}, ID: {}";
    /** constant FOUND_ERROR **/
    public final static String FOUND_ERROR = "{} found";
    /** constant SERVICE_LOG_INFO **/
    public final static String SERVICE_LOG_INFO = "Service {}: {}";
    /** constant UNAVAILABLE_SERVICE_INFO **/
    public final static String UNAVAILABLE_SERVICE_INFO = "Service {}: unavailable";
    /** constant UNAVAILABLE_SERVICE_DEBUG **/
    public final static String UNAVAILABLE_SERVICE_DEBUG = "Service {}: unavailable, response: {}";
    /** constant UNAVAILABLE_SERVICE_ERROR **/
    public final static String UNAVAILABLE_SERVICE_ERROR = "Service currently unavailable, please try again later!";
    /** constant INVALID_FORMAT **/
    public final static String INVALID_FORMAT = "Invalid format";
    /** constant INVALID_FIELD **/
    public final static String INVALID_FIELD = "Invalid field: {}";
    /** constant INTERNAL_SERVER_ERROR **/
    public final static String INTERNAL_SERVER_ERROR = "Internal Server Error: {}";

    /** constant ACCESS_DENIED **/
    public final static String SERVICE_UNAVAILABLE = "Service unavailable";

    /**
     *
     * Class constructor.
     *
     **/
    public ExceptionConst() {
    }
}
