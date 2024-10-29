package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.application.interfaces.IUserController;
import br.brazona.idp.api.domain.dto.business.UserDTO;
import br.brazona.idp.api.domain.services.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements IUserController {
    @Autowired
    private UserService userService;

    @Override
    public void create(UserDTO user) {
        userService.create(user);
    }

    @Override
    public ResponseEntity get(Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Override
    public ResponseEntity<List<UserDTO>> list() {
        return null;
    }

}
