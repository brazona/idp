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

public class MailConst {
    public final static String MSG_NOT_SEND_MAIL = "Não foi possível enviar o email com os dados de recuperação de acesso, tente novamente mais tarde.";
    public final static String MSG_SEND_MAIL = "Foi enviado no email cadastrado o código para recuperaração do acesso, verifique no email e informar para cadastrar nova senha.";
    public final static String SUBJECT_SEND_MAIL = "_USER_ esqueceu sua senha?";
    public final static String SUBJECT_SEND_MAIL_UPDATE_PASS = "_USER_ sua senha foi atualizada com sucesso!";
    public final static String MAIL_HOST = "mail.smtp.host";
    public final static String MAIL_AUTH = "mail.smtp.auth";
    public final static String MAIL_PORT = "mail.smtp.port";
    public final static String MAIL_FROM = "mail.smtp.from";
    public final static String MAIL_USERNAME = "mail.smtp.credential.username";
    public final static String MAIL_PASSWORD = "mail.smtp.credential.password";
    public final static String MAIL_SSL_PORT = "mail.smtp.ssl.port";
    public final static String MAIL_SSL_CLASS = "mail.smtp.ssl.class";

    public final static String MAIL_PROP_HOST = "mail.smtp.host";
    public final static String MAIL_PROP_AUTH = "mail.smtp.auth";
    public final static String MAIL_PROP_PORT = "mail.smtp.port";
    public final static String MAIL_PROP_FACTORY_PORT = "mail.smtp.socketFactory.port";
    public final static String MAIL_PROP_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    public final static String MAIL_HTML_UPDATE_PASS = "<!DOCTYPE html>\n" +
            "<html lang=\"pt-br\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <title>Atualização de Senha</title>\n" +
            "  <style>\n" +
            "    /* CSS inline para compatibilidade com clientes de email */\n" +
            "    body {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      margin: 0;\n" +
            "      padding: 0;\n" +
            "      background-color: #f4f4f4;\n" +
            "    }\n" +
            "\n" +
            "    .email-container {\n" +
            "      width: 100%;\n" +
            "      max-width: 600px;\n" +
            "      margin: 0 auto;\n" +
            "      background-color: #ffffff;\n" +
            "      padding: 20px;\n" +
            "      border-radius: 8px;\n" +
            "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
            "    }\n" +
            "\n" +
            "    .email-header {\n" +
            "      text-align: center;\n" +
            "      margin-bottom: 20px;\n" +
            "    }\n" +
            "\n" +
            "    .email-header img {\n" +
            "      width: 150px;\n" +
            "      height: auto;\n" +
            "    }\n" +
            "\n" +
            "    .email-body {\n" +
            "      font-size: 16px;\n" +
            "      line-height: 1.5;\n" +
            "      color: #333333;\n" +
            "      margin-bottom: 20px;\n" +
            "    }\n" +
            "\n" +
            "    .email-body p {\n" +
            "      margin: 10px 0;\n" +
            "    }\n" +
            "\n" +
            "    .cta-button {\n" +
            "      display: inline-block;\n" +
            "      background-color: #4CAF50;\n" +
            "      color: #ffffff;\n" +
            "      padding: 12px 20px;\n" +
            "      font-size: 16px;\n" +
            "      text-decoration: none;\n" +
            "      border-radius: 5px;\n" +
            "      text-align: center;\n" +
            "    }\n" +
            "\n" +
            "    .cta-button:hover {\n" +
            "      background-color: #45a049;\n" +
            "    }\n" +
            "\n" +
            "    .email-footer {\n" +
            "      text-align: center;\n" +
            "      font-size: 14px;\n" +
            "      color: #777777;\n" +
            "      margin-top: 20px;\n" +
            "    }\n" +
            "\n" +
            "    .email-footer a {\n" +
            "      color: #4CAF50;\n" +
            "      text-decoration: none;\n" +
            "    }\n" +
            "\n" +
            "    .email-footer a:hover {\n" +
            "      text-decoration: underline;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "  <div class=\"email-container\">\n" +
            "    <div class=\"email-header\">\n" +
            "      <img src=\"https://via.placeholder.com/150\" alt=\"Logo da Empresa\">\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"email-body\">\n" +
            "      <h2>Atualização de Senha</h2>\n" +
            "      <p>Olá, _USER_ </p>\n" +
            "      <p>Recebemos uma solicitação para atualizar sua senha." +
            "      \n" +
            "      \n" +
            "      <p>Se você não fez essa solicitação, por favor, ignore este e-mail ou entre em contato com nossa equipe de suporte.</p>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"email-footer\">\n" +
            "      <p>Se tiver dúvidas, acesse nossa <a href=\"https://exemplo.com/ajuda\">central de ajuda</a>.</p>\n" +
            "      <p>Atenciosamente, <br>Equipe de Suporte</p>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";
    public final static String MAIL_HTML_FORGOT = "<!DOCTYPE html>\n" +
            "<html lang=\"pt-br\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Esqueceu sua Senha</title>\n" +
            "    <style>\n" +
            "        /* Reseta alguns estilos padrão */\n" +
            "        * {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            background-color: #f7f7f7;\n" +
            "            color: #333;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .email-container {\n" +
            "            width: 100%;\n" +
            "            max-width: 600px;\n" +
            "            margin: 0 auto;\n" +
            "            background-color: #ffffff;\n" +
            "            border-radius: 8px;\n" +
            "            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .header {\n" +
            "            text-align: center;\n" +
            "            margin-bottom: 30px;\n" +
            "        }\n" +
            "\n" +
            "        .header img {\n" +
            "            max-width: 150px;\n" +
            "            margin-bottom: 15px;\n" +
            "        }\n" +
            "\n" +
            "        h1 {\n" +
            "            font-size: 24px;\n" +
            "            color: #333;\n" +
            "        }\n" +
            "\n" +
            "        p {\n" +
            "            font-size: 16px;\n" +
            "            line-height: 1.5;\n" +
            "            color: #555;\n" +
            "        }\n" +
            "\n" +
            "        .btn {\n" +
            "            display: inline-block;\n" +
            "            background-color: #007bff;\n" +
            "            color: #ffffff;\n" +
            "            padding: 12px 20px;\n" +
            "            font-size: 16px;\n" +
            "            text-decoration: none;\n" +
            "            border-radius: 5px;\n" +
            "            margin-top: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .footer {\n" +
            "            text-align: center;\n" +
            "            font-size: 12px;\n" +
            "            color: #999;\n" +
            "            margin-top: 30px;\n" +
            "        }\n" +
            "\n" +
            "        .footer p {\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "\n" +
            "        /* Responsividade */\n" +
            "        @media (max-width: 600px) {\n" +
            "            .email-container {\n" +
            "                padding: 15px;\n" +
            "            }\n" +
            "\n" +
            "            h1 {\n" +
            "                font-size: 20px;\n" +
            "            }\n" +
            "\n" +
            "            .btn {\n" +
            "                font-size: 14px;\n" +
            "                padding: 10px 18px;\n" +
            "            }\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "    <div class=\"email-container\">\n" +
            "        <!-- Cabeçalho -->\n" +
            "        <div class=\"header\">\n" +
            "             <img src=\"https://i.ibb.co/fFFpRfk/logotipo-idp.png\" alt=\"logotipo-idp\" border=\"0\">\n" +
            "            <h1>_USER_ , Esqueceu sua senha?</h1>\n" +
            "        </div>\n" +
            "\n" +
            "        <!-- Corpo do email -->\n" +
            "        <p>Olá,</p>\n" +
            "        <p>Parece que você solicitou a recuperação de senha para sua conta. Não se preocupe, é muito simples!</p>\n" +
            "        <p>Para redefinir sua senha, use o código de validação abaixo :</p>\n" +
            "            <h1>_NEW_PASS_</h1>\n" +
            "\n" +
            "        <p>Se você não solicitou a recuperação de senha, por favor, ignore este email.</p>\n" +
            "\n" +
            "        <!-- Rodapé -->\n" +
            "        <div class=\"footer\">\n" +
            "            <p>Obrigado por usar nosso serviço!</p>\n" +
            "            <p>Se você tiver algum problema, entre em contato com nosso suporte em <a href=\"mailto:support@seusite.com\">support@seusite.com</a>.</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";
    public final static String MAIL_HTML = "\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Recuperação de senha</title>\n" +
            "\t<meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<div dir=\"ltr\" style=\"margin:0; padding:0; background-color:#ffffff\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%;\" id=\"x_email_table\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td id=\"x_email_content\" style=\" font-family: 'Source Sans Pro', sans-serif; background:#ffffff\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "<tbody>\n" +
            "<tr style=\"\">\n" +
            "<td height=\"20\" colspan=\"3\" style=\"line-height:20px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td height=\"1\" colspan=\"3\" style=\"line-height:1px\"><span style=\"color:#FFFFFF; display:none!important; font-size:1px\">&nbsp; Alguém solicitou recentemente para redefinir sua senha do Sistema Brazona IDP. Clique aqui para cirar uma nova senha. Não solicitou esta alteração? Se você não solicitou a alteração de sua senha, desconsidere este e-mail . &nbsp;</span></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "<td style=\"\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "<tbody>\n" +
            "<tr style=\"\">\n" +
            "<td height=\"16\" colspan=\"3\" style=\"line-height:16px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td width=\"100%\" style=\"\"><a href=\"http://eventos.ifrn.edu.br/semadeczn/admin/index.php/site/login\" target=\"_blank\" style=\"color:rgb(60,141,188); text-decoration:none; font-family: 'Helvetica Neue', sans-serif; font-size:23px; line-height:32px\">Brazona IDP</a></td>\n" +
            "</tr>\n" +
            "<tr style=\"border-bottom:solid 1px #e5e5e5\">\n" +
            "<td height=\"16\" colspan=\"3\" style=\"line-height:16px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "<td style=\"\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "<tbody>\n" +
            "<tr style=\"\">\n" +
            "<td height=\"28\" style=\"line-height:28px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td style=\"\"><span style=\" font-family: 'Source Sans Pro', sans-serif; font-size:17px; line-height:21px; color:#141823\">\n" +
            "<p>Alguém solicitou recentemente para redefinir sua senha do Sistema Brazona IDP.</p>\n" +
            "<!-- coloca  o link para a alteração de senha -->\n" +
            "<p><a href=\"aqui \" target=\"_blank\" style=\"color:rgb(38,120,214); text-decoration:none\">NEW_PASS</a></p>\n" +
            "\n" +
            "<div><span style=\"color:#333333; font-weight:bold\">Não solicitou alteração?</span></div>\n" +
            "Se você não solicitou a alteração de sua senha, desconsidere este e-mail.</span></td>\n" +
            "</tr>\n" +
            "<tr style=\"\">\n" +
            "<td height=\"14\" style=\"line-height:14px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "<td style=\"\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "<tbody>\n" +
            "<tr style=\"\">\n" +
            "<td height=\"2\" colspan=\"3\" style=\"line-height:2px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td class=\"x_mb_blk\" style=\"\">\n" +
            "</td>\n" +
            "<td width=\"100%\" class=\"x_mb_hide\" style=\"\"></td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "<td style=\"\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:collapse\">\n" +
            "<tbody>\n" +
            "<tr style=\"border-top:solid 1px #e5e5e5\">\n" +
            "<td height=\"16\" style=\"line-height:16px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td style=\"font-family: 'Source Sans Pro', sans-serif; font-size:11px; color:#aaaaaa; line-height:16px\">\n" +
            "Essa mensagem foi enviada pela equipe Sistema Brazona IDP.<br>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>\n" +
            "<td width=\"15\" style=\"display:block; width:15px\">&nbsp;&nbsp;&nbsp;</td>\n" +
            "</tr>\n" +
            "<tr style=\"\">\n" +
            "<td height=\"20\" colspan=\"3\" style=\"line-height:20px\">&nbsp;</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div style=\"display: none;\"></div> \n" +
            "\n" +
            "</body>\n" +
            "</html>";

    /**
     *
     * Class constructor.
     *
     **/
    public MailConst() {
    }
}
