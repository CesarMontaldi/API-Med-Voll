package br.com.cesarmontaldi.model.paciente;

import br.com.cesarmontaldi.model.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
