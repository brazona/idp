package br.brazona.idp.api.controllers;

import br.brazona.idp.api.core.dtos.business.TokenDTO;
import br.brazona.idp.api.core.dtos.business.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/v1/user")
public interface IUserController {

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserDTO user);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity get(@PathVariable Long id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDTO>> list();
}
