package br.com.cesarmontaldi.service;

import br.com.cesarmontaldi.model.DadosCadastroMedico;
import br.com.cesarmontaldi.model.Medico;
import br.com.cesarmontaldi.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public void save(Medico dados) {

        medicoRepository.save(dados);
    }
}
