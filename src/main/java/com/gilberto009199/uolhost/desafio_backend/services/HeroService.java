package com.gilberto009199.uolhost.desafio_backend.services;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private HeroRepository heroRepository;
    private CodenameService codenameService;

    public HeroService(HeroRepository heroRepository, CodenameService codenameService){
        this.heroRepository = heroRepository;
        this.codenameService = codenameService;
    }

    public List<HeroEntity> list() {
        return heroRepository.findAll();
    }

    public HeroEntity create(HeroRequest request) {
        var entity = HeroEntity.of(request);

        var codename = codenameService.randomCodename(entity.getGrupo());

        entity.setCodename(codename);

        return heroRepository.save(entity);
    }

    public void clear() {
        heroRepository.deleteAll();
    }
}
