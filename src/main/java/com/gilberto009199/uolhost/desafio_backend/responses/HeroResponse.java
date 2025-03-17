package com.gilberto009199.uolhost.desafio_backend.responses;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;

public record HeroResponse(
        String name,
        String codename,
        String email,
        String telefone,
        GrupoResponse grupo
) {
    public static HeroResponse of(HeroEntity entity) {
        return new HeroResponse(
                entity.getName(),
                entity.getCodename(),
                entity.getEmail(),
                entity.getTelefone(),
                new GrupoResponse(entity.getGrupo())
        );
    }
}
