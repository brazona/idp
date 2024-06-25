package br.brazona.idp.api.services;

import br.brazona.idp.api.core.dtos.UserDTO;
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
        return usersEntity.map(entity -> new UserDTO(entity.getName())).orElse(null);
    }
    public UserDTO getByname(String name){
        UsersEntity usersEntity = usersRepository.findByName(name);
        return new UserDTO(usersEntity.getName());
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
}
