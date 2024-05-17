package br.com.cesarmontaldi.model.consulta.validacoes;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.repository.ConsultaRepository;
import br.com.cesarmontaldi.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteMarcarConsulta implements ValidadorAgendaConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var pacientePossuiOutraConsultaMarcada = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaMarcada) {
            throw new ValidationExceptions("Paciente já possui uma consulta marcada nesse dia!");
        }
    }
}
