package br.com.cesarmontaldi.model.medico;

import br.com.cesarmontaldi.model.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico( @NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
