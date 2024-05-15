package br.com.cesarmontaldi.service;

import br.com.cesarmontaldi.model.consulta.Consulta;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    public void agendar(DadosAgendamentoConsulta dados) {



        var medico = medicoService.getMedicoById(dados.idMedico());
        var paciente = pacienteService.getPacienteById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());

        repository.save(consulta);
    }
}
