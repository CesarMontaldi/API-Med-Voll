package br.com.cesarmontaldi.model.consulta.validacoes;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaHorarioConsultaMedico implements ValidadorAgendaConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiHorarioDisponivel = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiHorarioDisponivel) {
            throw new ValidationExceptions("Médico já possui outra consulta agendada nesse mesmo horário!");
        }
    }
}
