package com.gilberto009199.uolhost.desafio_backend.services;

import com.gilberto009199.uolhost.desafio_backend.controllers.GroupController;
import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    public static final Logger logger = LoggerFactory.getLogger(HeroService.class);

    private final HeroRepository heroRepository;
    private final CodenameService codenameService;

    public HeroService(HeroRepository heroRepository, CodenameService codenameService){
        this.heroRepository = heroRepository;
        this.codenameService = codenameService;
    }

    public List<HeroEntity> list() {

        logger.debug("stage=init method=list");

        var listHero = heroRepository.findAll();

        logger.debug("stage=end method=list {}", listHero);

        return listHero;
    }

    public HeroEntity create(HeroRequest request) {

        logger.debug("stage=init method=create {}", request);

        var entity = HeroEntity.of(request);

        var emailInUse = heroRepository.findByEmail(entity.getEmail());

        if(emailInUse.isPresent())throw new RuntimeException("Email em uso!");

        var codename = codenameService.randomCodename(entity.getGrupo());

        entity.setCodename(codename);

        logger.debug("stage=end method=create {}", entity);

        return heroRepository.save(entity);
    }

    public void clear() {
        logger.debug("stage=init method=clear");

        heroRepository.deleteAll();

        logger.debug("stage=end method=clear ");
    }
}
