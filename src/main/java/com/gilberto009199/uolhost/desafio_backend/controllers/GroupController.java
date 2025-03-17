package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.responses.GrupoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hero/group")
public class GroupController {

    public static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @GetMapping
    public ResponseEntity<?> list(){

        logger.debug("stage=init method=list ");

        var vectGroup = new GrupoResponse[]{
                new GrupoResponse( Grupo.vingadores ),
                new GrupoResponse( Grupo.ligaDaJustica )
        };

        logger.debug("stage=end method=list {}", vectGroup);

        return ResponseEntity.ok( vectGroup );
    }


}
