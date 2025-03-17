package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.responses.GrupoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hero/group")
public class GroupController {

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok( new GrupoResponse[]{
                new GrupoResponse( Grupo.vingadores ),
                new GrupoResponse( Grupo.ligaDaJustica )
        });
    }


}
