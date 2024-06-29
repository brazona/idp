package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.dtos.business.UserDTO;
import br.brazona.idp.api.services.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

}
