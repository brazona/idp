package br.brazona.idp.api.domain.services.business;


import br.brazona.idp.api.domain.constants.DictionaryConst;
import br.brazona.idp.api.domain.constants.MailConst;
import br.brazona.idp.api.domain.dto.AuthDTO;
import br.brazona.idp.api.domain.services.external.EmailService;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.brazona.idp.api.domain.utils.RandomPasswordGeneratorUtil.generateRandomPassword;

/**
* 
* Business rule service: [ AuthService ], which implements functionalities related to: [ Authorization and Authentication ] .
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Slf4j
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthDTO authDTO;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private EmailService emailService;
    /**
     *
     * Method constructor class.
     *
     **/
    public AuthService() {
    }

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param user Object of the User class, with the application's authentication values.
     * @return AuthResponseBusinessVO.class, view object
     *
     **/
    public AuthResponseBusinessVO authentication(AuthRequestBusinessVO user) {
        return authDTO.responseBusiness(loadUserByUsername(user.getUsername()), user);
    }

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param name  credentials for authentication, username and password for access registration.
     * @param password instance of the user details class ( UserDetails).
     * @param grantedAuths instance of the user details class ( UserDetails).
     * @return UsernamePasswordAuthenticationToken, username authentication.
     *
     **/

    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getByUsername(username);
    }

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param user_id  credentials for authentication, username and password for access registration.
     * @param access_token  credentials for authentication, username and password for access registration.
     * @return boolean, true witch valid and false witch not authorization.
     *
     **/
    public boolean authorization(Long user_id, String access_token) {
       SessionVO sessionVO = sessionService.getByUserId(user_id);
       Boolean isUser = userService.getUsernameByUpdate(sessionVO.getUser_id());
        return !isUser && sessionVO.getAccess_token().equals(access_token);
    }
    public ForgotResponseVO forgotPassword(AuthRequestBusinessVO authRequestBusinessVO){
        String randomPass = generateRandomPassword(10);
        UserRequestVO userRequestVO = userService.getUserVOByUsername(authRequestBusinessVO.getUsername());
        userRequestVO.setPassword(jwtUtils.bcryptEncryptor(randomPass));
        userRequestVO.setIsUpdatePassword(true);
        String subject = MailConst.SUBJECT_SEND_MAIL.replace("_USER_", userRequestVO.getName());
        String msg = MailConst.MAIL_HTML_FORGOT.replace("_USER_", userRequestVO.getName());
        msg = msg.replace("_NEW_PASS_",randomPass);
        Boolean valid = false;
        if (emailService.send(userRequestVO.getUsername(), subject, msg))
            valid = userService.createOrUpdate(userRequestVO);
        String message = valid ? MailConst.MSG_SEND_MAIL : MailConst.MSG_NOT_SEND_MAIL;
        return new ForgotResponseVO(valid, message);
    }

    public ForgotResponseVO updatePassword(UpdatePassRequestBusinessVO authUpdateRequestBusinessVO){
        UserRequestVO userRequestVO = userService.updatePassworUser(authUpdateRequestBusinessVO);
        userRequestVO.setPassword(jwtUtils.bcryptEncryptor(authUpdateRequestBusinessVO.getPasswordNew()));
        userRequestVO.setIsUpdatePassword(false);
        String subject = MailConst.SUBJECT_SEND_MAIL_UPDATE_PASS.replace("_USER_", userRequestVO.getName());
        String msg = MailConst.MAIL_HTML_UPDATE_PASS.replace("_USER_", userRequestVO.getName());
        Boolean valid = false;
        if (emailService.send(userRequestVO.getUsername(), subject, msg))
            valid = userService.createOrUpdate(userRequestVO);
        String message = valid ? MailConst.MSG_SEND_MAIL : MailConst.MSG_NOT_SEND_MAIL;
        return new ForgotResponseVO(valid, message);
    }
}