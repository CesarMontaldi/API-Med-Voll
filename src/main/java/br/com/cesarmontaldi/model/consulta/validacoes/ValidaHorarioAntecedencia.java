package br.com.cesarmontaldi.model.consulta.validacoes;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaHorarioAntecedencia implements ValidadorAgendaConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var horario = LocalDateTime.now();
        var tempoAntecedencia = Duration.between(horario, dataConsulta).toMinutes();

        if (tempoAntecedencia < 30) {
            throw new ValidationExceptions("Consulta deve ser agendada com antecedencia minima de 30 minutos!");
        }
    }
}
