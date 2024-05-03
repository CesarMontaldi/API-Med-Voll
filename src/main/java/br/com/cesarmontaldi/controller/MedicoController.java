package br.com.cesarmontaldi.controller;

import br.com.cesarmontaldi.model.DadosAtualizarMedico;
import br.com.cesarmontaldi.model.DadosCadastroMedico;
import br.com.cesarmontaldi.model.DadosListMedicos;
import br.com.cesarmontaldi.model.Medico;
import br.com.cesarmontaldi.repository.MedicoRepository;
import br.com.cesarmontaldi.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        service.salvar(new Medico(dados));
    }
    @GetMapping
    public Page<DadosListMedicos> listar(Pageable paginacao) {
        return service.findAllByAtivo(paginacao).map(DadosListMedicos::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = service.getMedicoById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        var medico = service.getMedicoById(id);
        medico.excluir();
    }
    
}
