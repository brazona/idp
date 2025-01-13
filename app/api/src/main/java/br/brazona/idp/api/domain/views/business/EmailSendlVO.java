package br.brazona.idp.api.domain.views.business;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
* 
* Class that transforms ForgotResponseVO data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Getter
@Component
public class EmailSendlVO {
    private String recipient;
    private String subject;
    private String content;

    public EmailSendlVO() {
    }

    public EmailSendlVO(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
}
