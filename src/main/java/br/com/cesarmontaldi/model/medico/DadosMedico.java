package br.com.cesarmontaldi.model.medico;

import br.com.cesarmontaldi.enums.Especialidade;
import br.com.cesarmontaldi.model.endereco.Endereco;

public record DadosMedico(Long ig, String nome, String email, String crm,  String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
