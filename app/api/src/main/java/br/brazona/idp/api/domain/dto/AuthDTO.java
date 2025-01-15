package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.constants.ExceptionConst;
import br.brazona.idp.api.domain.exceptions.AccessDeniedException;
import br.brazona.idp.api.domain.exceptions.BadRequestException;
import br.brazona.idp.api.domain.exceptions.NotFoundException;
import br.brazona.idp.api.domain.exceptions.UserNotFoundException;
import br.brazona.idp.api.domain.utils.ExceptionUtil;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthValidateCodeRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.UserRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/

@Slf4j
@Component
public class AuthDTO {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     *
     * Method constructor of class.
     *
     **/
    public AuthDTO() {
    }

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param name  credentials for authentication, username and password for access registration.
     * @param password instance of the user details class ( UserDetails).
     * @param grantedAuths instance of the user details class ( UserDetails).
     * @return UsernamePasswordAuthenticationToken, class with data user authentication.
     **/
    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param request  credentials for authentication, username and password for access registration.
     * @param vo instance of the user details class ( UserDetails).
     * @return AuthResponseBusinessVO, class with data token user.
     *
     **/
    public AuthResponseBusinessVO responseBusiness(UserDetails vo, AuthRequestBusinessVO request) {
        log.info("transform data auth response");
        validateVO(vo);
        validateRequest(request);
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        Authentication authentication = authenticateAgainstThirdPartyAndGetAuthentication(
                vo.getUsername(), vo.getPassword(), grantedAuths
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        log.info("validated match password");
        Boolean valid = jwtUtils.doPasswordsMatch(request.getPassword(), vo.getPassword());

        if (!valid) {
            log.error(ExceptionConst.ACCESS_DENIED);
            throw new AccessDeniedException(ExceptionConst.ACCESS_DENIED);
        }
        log.info("success validated match password");
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new AuthResponseBusinessVO(jwt);
    }

    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param vo instance of the user details class ( UserDetails).
     * @exception NotFoundException session not found.
     * @exception BadRequestException field not present
     *
     **/
    private void validateVO(UserDetails vo) {
        if (vo == null) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "user");
            throw new UserNotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (vo.getUsername() == null || vo.getUsername().isEmpty()) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "username");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "username"));
        } else if (vo.getPassword() == null || vo.getPassword().isEmpty()) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "password");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password"));
        }


    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param request credentials for authentication, username and password for access registration.
     *
     **/
    private void validateRequest(AuthRequestBusinessVO request) {
        if (request == null) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "user");
            throw new NotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "password");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password"));
        }

    }

    /**
     *
     * Method that provides the object with authentication data.
     *
     * @param validateCodeRequestBusinessVO  credentials for authentication, username and password for access registration.
     * @param userRequestVO instance of the user details class ( UserDetails).
     * @return AuthResponseBusinessVO, class with data token user.
     *
     **/
    public boolean userToValidateCode(AuthValidateCodeRequestBusinessVO validateCodeRequestBusinessVO,
                                                     UserRequestVO userRequestVO) {
        log.info("transform data auth validate code recovery");
        validateCodeRecoveryRequestVO(validateCodeRequestBusinessVO, userRequestVO);
        return true;
    }

    /**
     *
     * Method that provides the object with validate code recovery data.
     *
     * @param vo instance of the user class.
     * @param voCode instance of the code the recovery class.
     * @exception NotFoundException session not found.
     * @exception BadRequestException field not present
     *
     **/
    private void validateCodeRecoveryRequestVO(AuthValidateCodeRequestBusinessVO voCode, UserRequestVO vo) {
        if (vo == null) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "user");
            throw new UserNotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (vo.getUsername() == null || vo.getUsername().isEmpty()) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "username");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "username"));
        } else if (vo.getPassword() == null || vo.getPassword().isEmpty()) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "password");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password"));
        } else if (voCode == null) {
            log.error(ExceptionConst.NOT_FOUND_ERROR, "user");
            throw new UserNotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (voCode.getUsername() == null || voCode.getUsername().isEmpty()) {
            log.error(ExceptionConst.INVALID_FIELD, "username");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "username"));
        } else if (voCode.getCode() == null || voCode.getCode().isEmpty()) {
            log.error(ExceptionConst.INVALID_FIELD, "password");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "code"));
        } else if (!voCode.getUsername().equalsIgnoreCase(vo.getUsername())) {
            log.error(ExceptionConst.BAD_REQUEST_ERROR, "username");
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.BAD_REQUEST_ERROR, "username"));
        } else if (!jwtUtils.doPasswordsMatch(voCode.getCode(), vo.getPassword())) {
            log.error(ExceptionConst.ACCESS_DENIED, "username");
            throw new AccessDeniedException(ExceptionConst.ACCESS_DENIED);
        } else if (!vo.getIsUpdatePassword()) {
            log.error(ExceptionConst.ACCESS_DENIED, "username");
            throw new AccessDeniedException(ExceptionConst.ACCESS_DENIED);
        }



    }
}
