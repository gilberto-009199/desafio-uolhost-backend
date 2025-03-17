package com.gilberto009199.uolhost.desafio_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(new boolean[0]);
    }


    @PostMapping
    public ResponseEntity<?> save(){
        return ResponseEntity.ok(null);
    }


}
