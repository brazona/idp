package br.brazona.idp.api.domain.constants;

public class ExceptionConst {
    public final static String ACCESS_DENIED = "access denied";
    public final static String NOT_FOUND = "not found";
    public final static String NOT_FOUND_INFO = "{} not found: {}";
    public final static String NOT_FOUND_ERROR = "{} not found";
    public final static String BAD_REQUEST_ERROR = "{} bad format";
    public final static String FOUND_INFO = "{} found: {}";
    public final static String FOUND_DEBUG = "{} found: {}, ID: {}";
    public final static String FOUND_ERROR = "{} found";
    public final static String SERVICE_LOG_INFO = "Service {}: {}";
    public final static String UNAVAILABLE_SERVICE_INFO = "Service {}: unavailable";
    public final static String UNAVAILABLE_SERVICE_DEBUG = "Service {}: unavailable, response: {}";
    public final static String UNAVAILABLE_SERVICE_ERROR = "Service currently unavailable, please try again later!";
    public final static String INVALID_FORMAT = "Invalid format";
    public final static String INVALID_FIELD = "Invalid field: {}";
    public final static String INTERNAL_SERVER_ERROR = "Internal Server Error: {}";

}
