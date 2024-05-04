package br.com.cesarmontaldi.service;

import br.com.cesarmontaldi.model.Paciente;
import br.com.cesarmontaldi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Page<Paciente> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Paciente getPacienteById(Long id) {
        return repository.getReferenceById(id);
    }
}
