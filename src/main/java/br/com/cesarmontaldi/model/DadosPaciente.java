package br.com.cesarmontaldi.model;

public record DadosPaciente(Long id, String nome, String cpf, String email, String telefone, Endereco endereco) {

    public DadosPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}
