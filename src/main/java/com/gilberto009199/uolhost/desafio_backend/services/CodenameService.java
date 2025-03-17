package com.gilberto009199.uolhost.desafio_backend.services;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.repositories.CodenameRepository;
import com.gilberto009199.uolhost.desafio_backend.repositories.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodenameService {

    private HeroRepository heroRepository;
    private CodenameRepository codenameRepository;

    public CodenameService(HeroRepository heroRepository, CodenameRepository codenameRepository){
        this.heroRepository = heroRepository;
        this.codenameRepository = codenameRepository;
    }

    public String randomCodename(Grupo grupo) {
        var listCodenameInUse   = heroRepository.findCodenamesInUse();
        var listCodename        = codenameRepository.listInGroup(grupo);

        // select codename in not use
        var listCodenameAvailable = listCodename.stream()
                .filter(codename ->
                        !listCodenameInUse.contains(codename)
                ).toList();

        if(listCodenameAvailable.isEmpty())throw new RuntimeException("Apelidos dp Grupo Esgotados!!");

        return listCodenameAvailable.get(
                new Random().nextInt(
                    listCodenameAvailable.size()
                )
        );
    }
}
