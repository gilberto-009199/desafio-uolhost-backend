package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import com.gilberto009199.uolhost.desafio_backend.responses.HeroResponse;
import com.gilberto009199.uolhost.desafio_backend.services.HeroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping
    public ResponseEntity<?> list(){
        var listEntity = heroService.list();

        var listResponse = listEntity
        .stream()
        .map(HeroResponse::of)
        .toList();

        return ResponseEntity.ok(listResponse);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid HeroRequest request){

        var entity = heroService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(HeroResponse.of(entity));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        heroService.clear();
        return ResponseEntity.ok(null);
    }

}
