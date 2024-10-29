package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.business.UserDetailsImplDTO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetailsImplDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetailsImplDTO.build(user);
    }
}
