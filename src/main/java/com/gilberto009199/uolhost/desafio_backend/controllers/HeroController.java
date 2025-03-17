package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    private HeroRepository heroRepository;

    public HeroController(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(heroRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid HeroRequest request){
        var entity = heroRepository.save(HeroEntity.of(request));
        return ResponseEntity.ok(entity);
    }


}
