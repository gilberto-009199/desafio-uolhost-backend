package com.gilberto009199.uolhost.desafio_backend.requests;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HeroRequest(
        @NotBlank(message = "Nome é Obrigatorio")
        String name,
        @NotBlank(message = "Email é Obrigatorio")
        @Email
        String email,
        String telefone,
        @NotNull(message = "Grupo é Obrigatorio")
        Grupo grupo
) {
}
