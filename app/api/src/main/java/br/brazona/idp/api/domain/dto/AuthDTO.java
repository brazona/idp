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

    public AuthResponseBusinessVO responseBusiness(UserDetails vo, AuthRequestBusinessVO request) {
        validateVO(vo);
        validateRequest(request);
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        Authentication authentication = authenticateAgainstThirdPartyAndGetAuthentication(
                vo.getUsername(), vo.getPassword(), grantedAuths
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        Boolean valid = jwtUtils.doPasswordsMatch(request.getPassword(), vo.getPassword());

        if (!valid)
            throw new AccessDeniedException(ExceptionConst.ACCESS_DENIED);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new AuthResponseBusinessVO(jwt);
    }

    private void validateVO(UserDetails vo) {
        if (vo == null) {
            throw new UserNotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (vo.getUsername() == null || vo.getUsername().isEmpty()) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "username"));
        } else if (vo.getPassword() == null || vo.getPassword().isEmpty()) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password"));
        }

    }

    private void validateRequest(AuthRequestBusinessVO request) {
        if (request == null) {
            throw new NotFoundException(
                    exceptionUtil.replaceKey(ExceptionConst.NOT_FOUND_ERROR, "user"));
        } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new BadRequestException(
                    exceptionUtil.replaceKey(ExceptionConst.INVALID_FIELD, "password"));
        }

    }
}
