package br.brazona.idp.api.services.business;

import br.brazona.idp.api.core.dtos.business.UserDTO;
import br.brazona.idp.api.core.dtos.business.UserDetailsDTO;
import br.brazona.idp.api.core.exception.UserNotFoundException;
import br.brazona.idp.api.persistence.entities.UsersEntity;
import br.brazona.idp.api.persistence.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersEntity usersEntity;
    @Autowired
    private UserDTO userDTO;

    public void create(UserDTO userDTO){
        usersEntity.setName(userDTO.getUsername());
        usersRepository.save(usersEntity);
    }

    public UserDTO getById(Long id){
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);
        if (usersEntity.isEmpty()){
            throw new UserNotFoundException();
        }
        return usersEntity.map(entity -> new UserDTO(entity.getId() ,entity.getName())).orElse(null);
    }
    public UserDTO getByname(String name){
        UsersEntity entity = usersRepository.findByName(name);
        return new UserDTO(entity.getId() ,entity.getName());
    }
    public Long getIdByname(String name){
        UsersEntity usersEntity = usersRepository.findByName(name);
        return usersEntity.getId();
    }
    public void isPresentById (Long id){

        if (getById(id) == null){
            throw new UserNotFoundException();
        };
    }
    public void isPresentByName (String name){

        if (getByname(name) == null){
            throw new UserNotFoundException();
        };
    }

    public UserDetailsDTO getDetailsByName(String username) {
        UsersEntity user = usersRepository.findByUsername(username);
        if (user == null){
            throw new UserNotFoundException();
        }
        return new UserDetailsDTO(user);
    }
}