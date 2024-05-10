package br.com.cesarmontaldi.model;

import br.com.cesarmontaldi.enums.Especialidade;

public record DadosListMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
