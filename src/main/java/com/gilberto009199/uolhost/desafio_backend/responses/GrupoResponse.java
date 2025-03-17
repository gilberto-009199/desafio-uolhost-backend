package com.gilberto009199.uolhost.desafio_backend.responses;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;

public record GrupoResponse(
        String nome,
        Grupo grupo
) {
    public GrupoResponse(Grupo grupo) {
        this(grupo.name, grupo);
    }
}
