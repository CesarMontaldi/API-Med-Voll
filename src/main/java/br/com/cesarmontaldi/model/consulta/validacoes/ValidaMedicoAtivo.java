package br.com.cesarmontaldi.model.consulta.validacoes;

import br.com.cesarmontaldi.model.ValidationExceptions;
import br.com.cesarmontaldi.model.consulta.DadosAgendamentoConsulta;
import br.com.cesarmontaldi.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements ValidadorAgendaConsulta {

    @Autowired
    private MedicoService medicoService;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoAtivo = medicoService.medicoAtivoId(dados.idMedico());
        if (!medicoAtivo) {
            throw new ValidationExceptions("Consulta não pode ser agendada com médico excluido!");
        }
    }
}
