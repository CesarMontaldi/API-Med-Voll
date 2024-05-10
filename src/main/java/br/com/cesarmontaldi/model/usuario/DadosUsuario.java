package br.com.cesarmontaldi.model.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosUsuario(

        @NotBlank
        String login,

        @NotBlank
        String senha) {
}
