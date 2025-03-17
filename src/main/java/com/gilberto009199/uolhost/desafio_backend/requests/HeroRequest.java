package com.gilberto009199.uolhost.desafio_backend.requests;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;

public record HeroRequest(
        String name,
        String email,
        String telefone,
        Grupo grupo
) {
}
