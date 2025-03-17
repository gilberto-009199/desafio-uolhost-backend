package com.gilberto009199.uolhost.desafio_backend.services;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.repositories.CodenameRepository;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodenameService {

    public static final Logger logger = LoggerFactory.getLogger(CodenameService.class);

    private final HeroRepository heroRepository;
    private final CodenameRepository codenameRepository;

    public CodenameService(HeroRepository heroRepository, CodenameRepository codenameRepository){
        this.heroRepository = heroRepository;
        this.codenameRepository = codenameRepository;
    }

    public String randomCodename(Grupo grupo) {

        logger.debug("stage=init method=randomCodename {}", grupo);

        var listCodenameInUse   = heroRepository.findCodenamesInUse();

        logger.debug("stage=init method=randomCodename {}", listCodenameInUse);

        var listCodename        = codenameRepository.listInGroup(grupo);

        logger.debug("stage=init method=randomCodename {}", listCodename);

        // select codename in not use
        var listCodenameAvailable = listCodename.stream()
                .filter(codename ->
                        !listCodenameInUse.contains(codename)
                ).toList();

        logger.debug("stage=middle method=randomCodename {}", listCodenameAvailable);


        if(listCodenameAvailable.isEmpty())throw new RuntimeException("Apelidos do Grupo Esgotados!!");

        logger.debug("stage=middle method=randomCodename {}", listCodenameAvailable);

        var codename = listCodenameAvailable.get(
                new Random().nextInt(
                        listCodenameAvailable.size()
                )
        );

        logger.debug("stage=end method=randomCodename {}", codename);

        return codename;
    }
}
