package br.com.cesarmontaldi.controller;

import br.com.cesarmontaldi.model.DadosAtualizarPaciente;
import br.com.cesarmontaldi.model.DadosCadastroPaciente;
import br.com.cesarmontaldi.model.DadosListPacientes;
import br.com.cesarmontaldi.model.Paciente;
import br.com.cesarmontaldi.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        service.salvar(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListPacientes> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return service.listar(paginacao).map(DadosListPacientes::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = service.getPacienteById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        var paciente = service.getPacienteById(id);
        paciente.excluir();
    }

    @PutMapping("/{id}")
    @Transactional
    public void ativarCadastroPaciente(@PathVariable Long id) {
        var paciente = service.getPacienteById(id);
        paciente.ativarCadastro();
    }
}
