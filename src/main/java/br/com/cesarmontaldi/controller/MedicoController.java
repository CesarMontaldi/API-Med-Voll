package br.com.cesarmontaldi.controller;


import br.com.cesarmontaldi.model.medico.*;
import br.com.cesarmontaldi.service.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        service.salvar(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosMedico(medico));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarMedico(@PathVariable Long id) {
        Medico medico = service.getMedicoById(id);
        if (medico.isAtivo()) {
            return ResponseEntity.ok(new DadosMedico(medico));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListMedicos>> listar(Pageable paginacao) {
        var medicos = service.findAllByAtivo(paginacao).map(DadosListMedicos::new);

        return ResponseEntity.ok(medicos);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = service.getMedicoById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var medico = service.getMedicoById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarCadastroMedico(@PathVariable Long id) {
        var medico = service.getMedicoById(id);
        medico.ativarCadastro();

        return ResponseEntity.ok().build();
    }

}
