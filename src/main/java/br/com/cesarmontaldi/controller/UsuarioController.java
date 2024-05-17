package br.com.cesarmontaldi.controller;

import br.com.cesarmontaldi.model.usuario.DadosUsuario;
import br.com.cesarmontaldi.model.usuario.Usuario;
import br.com.cesarmontaldi.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid DadosUsuario dados) {

        service.salvar(new Usuario(dados));
        return ResponseEntity.ok().build();
    }
}
