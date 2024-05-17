package br.com.cesarmontaldi.service;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.Consulta;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.model.consulta.DadosConsulta;
import br.com.cesarmontaldi.model.consulta.validacoes.ValidadorAgendaConsulta;
import br.com.cesarmontaldi.model.medico.Medico;
import br.com.cesarmontaldi.repository.ConsultaRepository;
import br.com.cesarmontaldi.repository.MedicoRepository;
import br.com.cesarmontaldi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendaConsulta> validadores;

    public DadosConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidationExceptions("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidationExceptions("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = medicoEscolhido(dados);
        if (medico == null) {
            throw new ValidationExceptions("Nenhum médico disponível nessa data!");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data());

        repository.save(consulta);

        return new DadosConsulta(consulta);
    }

    private Medico medicoEscolhido(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidationExceptions("Especialidade é obrigatória quando o médico não for informado!");
        }

        return medicoRepository.escolherMedicoAleatorioData(dados.especialidade(), dados.data());
    }

}
