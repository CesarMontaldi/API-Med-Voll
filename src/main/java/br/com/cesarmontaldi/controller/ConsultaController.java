package br.com.cesarmontaldi.controller;

import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.model.consulta.DadosConsulta;
import br.com.cesarmontaldi.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        consultaService.agendar(dados);
        return ResponseEntity.ok(new DadosConsulta(null, null, null, null));
    }

}
