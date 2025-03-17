package com.gilberto009199.uolhost.desafio_backend.entities;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "hero")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String telefone;

    @NotBlank
    @Column(unique = true)
    private String codename;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Grupo grupo;

    public static HeroEntity of(HeroRequest request) {
        return new HeroEntity()
                .setEmail(request.email())
                .setTelefone(request.telefone())
                .setName(request.name())
                .setGrupo(request.grupo());
    }

    public long getId() { return id;  }
    public HeroEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() { return name; }
    public HeroEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() { return email; }
    public HeroEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefone() { return telefone; }
    public HeroEntity setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Grupo getGrupo() { return grupo;  }
    public HeroEntity setGrupo(Grupo grupo) {
        this.grupo = grupo;
        return this;
    }

    public String getCodename(){ return this.codename; }
    public HeroEntity setCodename(String codename){
        this.codename = codename;
        return this;
    }

}
