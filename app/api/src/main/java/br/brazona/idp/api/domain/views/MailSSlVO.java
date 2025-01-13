package br.brazona.idp.api.domain.views;

import br.brazona.idp.api.domain.constants.MailConst;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
* 
* Class that transforms MailTLSVO data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Component
@Getter
public class MailSSlVO {
    private String host;
    private String port_factory;
    private String className;
    private String auth;
    private String port;
    private String username;
    private String password;
    private String from;
    private Properties props;

    /**
     *
     * Method constructor class.
     *
     **/
    public MailSSlVO() {
    }
    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param host Object of the User class, with the application's authentication values.
     * @param port Object of the User class, with the application's authentication values.
     * @param auth Object of the User class, with the application's authentication values.
     * @param username Object of the User class, with the application's authentication values.
     * @param password Object of the User class, with the application's authentication values.
     *
     **/
    public MailSSlVO(String host, String port, String className, String auth, String username,
                     String password, String from) {
        this.host = host;
        this.port_factory = port;
        this.className = className;
        this.auth = auth;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
        Properties properties = new Properties();
        properties.put(MailConst.MAIL_PROP_HOST, host);
        properties.put(MailConst.MAIL_PROP_AUTH, auth);
        properties.put(MailConst.MAIL_PROP_PORT, port);
        properties.put(MailConst.MAIL_PROP_FACTORY_PORT, port);
        properties.put(MailConst.MAIL_PROP_FACTORY_CLASS, className);

        this.props = properties;
    }
}
