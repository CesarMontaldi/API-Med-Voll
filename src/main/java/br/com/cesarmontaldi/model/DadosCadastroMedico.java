package br.com.cesarmontaldi.model;

import br.com.cesarmontaldi.enums.Especialidade;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
