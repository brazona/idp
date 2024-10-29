package br.brazona.idp.api.domain.services;

import br.brazona.idp.api.domain.dto.UserDTO;
import br.brazona.idp.api.domain.dto.UserDetailsDTO;
import br.brazona.idp.api.domain.dto.UserDetailsImplDTO;
import br.brazona.idp.api.domain.exceptions.UserNotFoundException;
import br.brazona.idp.api.infrastructure.entities.AddressesEntity;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersEntity usersEntity;
    @Autowired
    private UserDTO userDTO;
    @Autowired
    private AddressesEntity addressesEntity;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByUsername(username);

        if (user == null){
            throw new UserNotFoundException();
        };
        return UserDetailsImplDTO.build(user);
    }
    public UserDetailsDTO getDetailsByName(String username) {
        UsersEntity user = usersRepository.findByUsername(username);
        if (user == null){
            throw new UserNotFoundException();
        }
        return new UserDetailsDTO(user);
    }
}
