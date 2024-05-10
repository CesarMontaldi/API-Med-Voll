package br.com.cesarmontaldi.model.medico;

import br.com.cesarmontaldi.enums.Especialidade;
import br.com.cesarmontaldi.model.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record DadosCadastroMedico(

            @NotBlank
            String nome,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String telefone,

            @NotBlank
            @Pattern(regexp = "\\d{4,6}")
            String crm,

            @NotNull
            Especialidade especialidade,

            @NotNull
            @Valid
            DadosEndereco endereco) {
}
