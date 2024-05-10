package br.com.cesarmontaldi.controller;


import br.com.cesarmontaldi.model.paciente.*;
import br.com.cesarmontaldi.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        service.salvar(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListPacientes>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pacientes = service.listar(paginacao).map(DadosListPacientes::new);

        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPaciente(@PathVariable Long id) {
        var paciente = service.getPacienteById(id);

        return ResponseEntity.ok(new DadosPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = service.getPacienteById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var paciente = service.getPacienteById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarCadastroPaciente(@PathVariable Long id) {
        var paciente = service.getPacienteById(id);
        paciente.ativarCadastro();

        return new ResponseEntity<>("Paciente ativo novamente!", HttpStatus.OK);
    }
}
