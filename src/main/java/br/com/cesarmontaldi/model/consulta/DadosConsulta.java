package br.com.cesarmontaldi.model.consulta;

import java.time.LocalDateTime;

public record DadosConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
