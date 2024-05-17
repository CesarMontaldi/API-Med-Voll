package br.com.cesarmontaldi.model.consulta.validacoes;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendaConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var aberturaClinica = dataConsulta.getHour() < 7;
        var fechamentoClinica = dataConsulta.getHour() > 18;

        if (domingo || aberturaClinica || fechamentoClinica) {
            throw new ValidationExceptions("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
