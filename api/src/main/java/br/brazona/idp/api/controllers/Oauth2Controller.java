package br.brazona.idp.api.controllers;

import br.brazona.idp.core.interfaces.IOauth2Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/oauth2")
public class Oauth2Controller extends IOauth2Controller {

}
