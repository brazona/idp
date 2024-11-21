package br.brazona.idp.api.domain.dto;

import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import br.brazona.idp.api.domain.views.keycloak.AuthRequestKeycloakVO;
import br.brazona.idp.api.domain.views.keycloak.AuthResponseKeycloakVO;
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

    public AuthRequestBusinessVO requestBusiness(AuthRequestKeycloakVO vo){
        return new AuthRequestBusinessVO();
    }

    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }

    public AuthRequestKeycloakVO requestKeycloak(AuthRequestBusinessVO vo){

        return new AuthRequestKeycloakVO();
    }
    public AuthResponseKeycloakVO responseKeycloak(AuthResponseBusinessVO vo){
        return new AuthResponseKeycloakVO();
    }

    public AuthResponseBusinessVO responseBusiness(UserDetails vo, AuthRequestBusinessVO request) {

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        Authentication authentication = authenticateAgainstThirdPartyAndGetAuthentication(
                vo.getUsername(), vo.getPassword(), grantedAuths
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String passEncoder = jwtUtils.bcryptEncryptor(request.getPassword());
        Boolean valid = jwtUtils.doPasswordsMatch(request.getPassword(), vo.getPassword());

        if (!valid)
            throw new RuntimeException("password invalido");
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new AuthResponseBusinessVO(jwt);
    }
}
