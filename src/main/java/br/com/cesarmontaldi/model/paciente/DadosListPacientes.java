package br.com.cesarmontaldi.model;

public record DadosListPacientes(Long id, String nome, String cpf, String email) {

    public DadosListPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail());
    }
}
