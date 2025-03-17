package com.gilberto009199.uolhost.desafio_backend.repositories;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<HeroEntity, Long> {}
