package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import com.gilberto009199.uolhost.desafio_backend.responses.HeroResponse;
import com.gilberto009199.uolhost.desafio_backend.services.HeroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    public static final Logger logger = LoggerFactory.getLogger(HeroController.class);

    private final HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping
    public ResponseEntity<?> list(){
        logger.debug("stage=init method=list");

        var listEntity = heroService.list();

        logger.debug("stage=middle method=list {}", listEntity);

        var listResponse = listEntity
        .stream()
        .map(HeroResponse::of)
        .toList();

        logger.debug("stage=end method=list {}", listResponse);

        return ResponseEntity.ok(listResponse);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid HeroRequest request){

        logger.debug("stage=init method=save {}", request);

        var entity = heroService.create(request);

        logger.debug("stage=end method=save {}", entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(HeroResponse.of(entity));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){

        logger.debug("stage=init method=delete ");

        heroService.clear();

        logger.debug("stage=end method=, entity");

        return ResponseEntity.ok(null);
    }

}
