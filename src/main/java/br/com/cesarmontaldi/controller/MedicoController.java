package br.com.cesarmontaldi.controller;

import br.com.cesarmontaldi.model.DadosCadastroMedico;
import br.com.cesarmontaldi.model.Medico;
import br.com.cesarmontaldi.repository.MedicoRepository;
import br.com.cesarmontaldi.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        service.save(new Medico(dados));
    }
}
