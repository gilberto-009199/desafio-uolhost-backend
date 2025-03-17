package com.gilberto009199.uolhost.desafio_backend.controllers;

import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.gilberto009199.uolhost.desafio_backend.responses.GrupoResponse;
import com.gilberto009199.uolhost.desafio_backend.responses.HeroResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class GroupControllerTests {

    @Autowired
    protected TestRestTemplate restAPI;
    protected String path = "/api/hero/group";

    @Test
    void testListGroup() {

        var response = restAPI.getForEntity( path, GrupoResponse[].class);
        var listGroup = response.getBody();

        Assertions.assertEquals( HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull( listGroup );

        Assertions.assertEquals( Grupo.values().length, listGroup.length);
    }
}
