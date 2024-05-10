package br.com.cesarmontaldi.model;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico( @NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
