package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.requests.HeroRequest;
import com.gilberto009199.uolhost.desafio_backend.responses.HeroResponse;
import com.gilberto009199.uolhost.desafio_backend.services.HeroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HeroControllerTests {

    @Autowired
    protected TestRestTemplate restAPI;
    protected String path = "/api/hero";

    @Autowired
    private HeroService heroService;

    @Test
    void testSaveHero() {

        var request = new HeroRequest(
                "Mustarda Humana",
                "mustarda@humana.com",
                "1111125659",
                Grupo.vingadores
        );

        var response = restAPI.postForEntity( path, request, HeroResponse.class);
        var heroResponse = response.getBody();

        Assertions.assertEquals( HttpStatus.CREATED, response.getStatusCode());

        Assertions.assertNotNull( heroResponse );

        Assertions.assertEquals( request.name(), heroResponse.name());
        Assertions.assertEquals( request.email(), heroResponse.email());
        Assertions.assertEquals( request.grupo(), heroResponse.grupo().grupo());

    }

    @Test
    void testListHeroes() {
        var countInService = heroService.list().size();

        var response = restAPI.getForEntity( path, HeroResponse[].class);
        var listHero = response.getBody();

        Assertions.assertEquals( HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull( listHero );

        Assertions.assertEquals( countInService, listHero.length);

    }

    @Test
    void testDeleteHeroes() {
        var countInService = heroService.list().size();

        restAPI.delete( path );

        var response = restAPI.getForEntity( path, HeroResponse[].class);
        var listHero = response.getBody();

        Assertions.assertEquals( HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull( listHero );

        Assertions.assertEquals( 0, listHero.length);
    }

}
