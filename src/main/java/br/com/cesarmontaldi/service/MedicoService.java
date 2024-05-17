package br.com.cesarmontaldi.service;


import br.com.cesarmontaldi.enums.Especialidade;
import br.com.cesarmontaldi.model.medico.Medico;
import br.com.cesarmontaldi.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Medico salvar(Medico medico) {
       return repository.save(medico);
    }

    public Page<Medico> findAllByAtivo(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Boolean medicoAtivoId(Long id) {
        return repository.findAtivoById(id);
    }

    public Medico getMedicoById(Long id) {
        return repository.getReferenceById(id);
    }

    public Boolean existsMedicoId(Long id) {
        return repository.existsById(id);
    }

    public Medico escolherMedico(Especialidade especialidade, LocalDateTime data) {

        return repository.escolherMedicoAleatorioData(especialidade, data);
    }

}
