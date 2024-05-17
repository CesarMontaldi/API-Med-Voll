package br.com.cesarmontaldi.model.consulta.validacoes;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.repository.PacienteRepository;
import br.com.cesarmontaldi.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendaConsulta {

    @Autowired
    private PacienteService pacienteService;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteIsAtivo = pacienteService.pacienteAtivoById(dados.idPaciente());

        if (!pacienteIsAtivo) {
            throw new ValidationExceptions("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
