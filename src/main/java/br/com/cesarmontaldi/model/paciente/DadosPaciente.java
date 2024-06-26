package br.com.cesarmontaldi.model.paciente;

import br.com.cesarmontaldi.model.endereco.Endereco;
import br.com.cesarmontaldi.model.paciente.Paciente;

public record DadosPaciente(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {

    public DadosPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}
