package com.gilberto009199.uolhost.desafio_backend.enums;

public enum Grupo {

    vingadores("Os Vingadores"),
    ligaDaJustica("Liga da Justiça");

    public String name;

    Grupo(String name){
        this.name = name;
    }

    public void setName(String name) { this.name = name; }
    public String getName() {
        return name;
    }
}
