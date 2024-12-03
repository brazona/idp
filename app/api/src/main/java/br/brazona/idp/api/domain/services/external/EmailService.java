package br.brazona.idp.api.domain.services.external;

import br.brazona.idp.api.domain.constants.MailConst;
import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.dto.MailDTO;
import br.brazona.idp.api.domain.exceptions.EmailNotSendException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.views.MailSSlVO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private MailDTO mailDTO;
    @Autowired
    private ExceptionUtil exceptionUtil;
    final String username = "cezarfelipe2008@gmail.com";
    final String password = "imlhlsxvwxgsgusr";
    final String send_mail = "brazonatech@gmail.com";

    public Boolean send(String email, String subject, String messageMail){

        log.info("Iniciado envio de email para recuperação de senha do usuário: {}", email);
        MailSSlVO mailSSlVO = mailDTO.propertiesTotTlsVo();
        log.debug("propriedades: {}", mailSSlVO.getProps());
        log.debug("usuário: {} e senha {}", mailSSlVO.getUsername(), mailSSlVO.getPassword());
        Session session = Session.getInstance(mailSSlVO.getProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                        //return new PasswordAuthentication(mailSSlVO.getUsername(), mailSSlVO.getPassword());
                    }
                });
        session.setDebug(true);
        try {

            Address[] toUser = InternetAddress.parse(email);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);
            //message.setText(msg);
            message.setContent(messageMail, "text/html");
            log.info("__________________________________________________");
            log.info("Enviando email DE: {} PARA: {}", mailSSlVO.getUsername(), email);
            log.info("Assunto: {}", subject);
            Transport.send(message);
            log.info("Email enviado com sucesso para o usuário: {}", email);
            return true;
        } catch (MessagingException e) {
            log.error("Falha no envio de email para o usuário: {}", email);
            log.error(e.getLocalizedMessage());
            throw new EmailNotSendException(ExceptionConst.SERVICE_UNAVAILABLE);
        }
    }
    public void send1(UserDetailsVO user){
        log.info("send email");
        MailSSlVO mailSSlVO = mailDTO.propertiesTotTlsVo();
        log.debug(mailSSlVO.getProps().toString());
        Session session = Session.getInstance(mailSSlVO.getProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(mailSSlVO.getUsername(), mailSSlVO.getPassword());
                    }
                });

        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            Address[] list = InternetAddress.parse(user.getUsername());
            message.setRecipients(Message.RecipientType.TO, list);
            message.setSubject(MailConst.SUBJECT_SEND_MAIL.replace("_USER_", user.getName()));
            message.setText(MailConst.MAIL_HTML_FORGOT.replace("_NEW_PASS_",user.getPassword()));
            Transport.send(message);

            log.info("send email protocolo SSL");

        } catch (MessagingException e) {
            log.error("send email");
            log.error(e.getLocalizedMessage());
            email_0(user);
            //throw new EmailNotSendException(ExceptionConst.SERVICE_UNAVAILABLE);
        }
    }
    public void email_0(UserDetailsVO user){
        log.info("iniciando email_0");
        MailSSlVO mailSSlVO = mailDTO.propertiesTotTlsVo();
        log.debug(mailSSlVO.getProps().toString());
        Session session = Session.getInstance(mailSSlVO.getProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username, password);
                    }
                });

//        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            Address[] list = InternetAddress.parse(send_mail);
            message.setFrom(new InternetAddress("cezarfelipe2008@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, list);
            message.setSubject(MailConst.SUBJECT_SEND_MAIL.replace("_USER_", user.getName()));
            message.setText(MailConst.MAIL_HTML_FORGOT.replace("_NEW_PASS_",user.getPassword()));
            Transport.send(message);

            log.info("Exemplo 0 enviado com sucesso!");

        } catch (MessagingException e) {
            log.error("erro no email_0");
            log.error(e.getLocalizedMessage());
            email_1();
            //throw new EmailNotSendException(ExceptionConst.SERVICE_UNAVAILABLE);
        }
    }
    public void email_1(){
        log.info("iniciando email_1");
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username, password);
                    }
                });

        /** Ativa Debug para sessão */
        //session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(send_mail);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com JavaMail");//Assunto
            message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            log.info("Exemplo 1 enviado com sucesso!");

        } catch (MessagingException e) {
            log.error("erro email_1");
            log.error(e.getLocalizedMessage());
            email_2();
        }
    }
    public void email_2(){
        log.info("iniciando email_2");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(send_mail)
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            log.info("Exemplo 2 enviado com sucesso!");

        } catch (MessagingException e) {
            log.error("iniciando email_2");
            log.error(e.getLocalizedMessage());
            throw new EmailNotSendException(ExceptionConst.SERVICE_UNAVAILABLE);
        }
    }
}
