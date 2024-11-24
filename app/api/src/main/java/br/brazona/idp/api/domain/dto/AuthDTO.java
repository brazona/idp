/**
 * @author Brazona Tech
 **/
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

@Slf4j
@Component
public class AuthDTO {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ExceptionUtil exceptionUtil;

    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
    /**
     * @param request  credentials for authentication, username and password for access registration.
     * @param vo instance of the user details class ( UserDetails).
     * @return ResponseEntity<Object> excess relative to the process.
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
     * @param vo instance of the user details class ( UserDetails).
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
     * @param request credentials for authentication, username and password for access registration.
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
}
