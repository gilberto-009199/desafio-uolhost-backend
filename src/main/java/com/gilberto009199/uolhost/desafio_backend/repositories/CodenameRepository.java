package com.gilberto009199.uolhost.desafio_backend.repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilberto009199.uolhost.desafio_backend.controllers.GroupController;
import com.gilberto009199.uolhost.desafio_backend.enums.Grupo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gilberto009199.uolhost.desafio_backend.enums.Grupo.*;

@Repository
public class CodenameRepository {

    public static final Logger logger = LoggerFactory.getLogger(CodenameRepository.class);

    private final RestClient restClient;
    private final ObjectMapper mapper;

    @Value("${uri.vingadores}")
    private String vingadoresUri;

    @Value("${uri.ligaDaJustica}")
    private String ligaDaJusticaUri;

    public CodenameRepository(ObjectMapper mapper){
        this.restClient = RestClient.create();
        this.mapper = mapper;
    }

    public List<String> listInGroup(Grupo grupo){

        logger.debug("stage=init method=listInGroup {}", grupo);

        var listCodename =  switch (grupo) {
            case vingadores -> listVingadores();
            case ligaDaJustica -> listLigaDaJustica();
        };

        logger.debug("stage=end method=listInGroup {}", listCodename);

        return listCodename;
    }
    private List<String> listVingadores() {
        String json = fetchJson(vingadoresUri);
        return extractCodenamesFromJson(json);
    }

    private List<String> listLigaDaJustica() {
        String xml = fetchXml(ligaDaJusticaUri);
        return extractCodenamesFromXml(xml);
    }

    private String fetchJson(String url) {
        ResponseEntity<String> response = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao buscar JSON da URL: " + url);
        }

        return response.getBody();
    }

    private String fetchXml(String url) {
        ResponseEntity<String> response = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao buscar XML da URL: " + url);
        }

        return response.getBody();
    }

    private List<String> extractCodenamesFromJson(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray vingadoresArray = jsonObject.getAsJsonArray("vingadores");

        return IntStream.range(0, vingadoresArray.size())
                .mapToObj(i -> vingadoresArray.get(i).getAsJsonObject().get("codinome").getAsString())
                .collect(Collectors.toList());
    }

    private List<String> extractCodenamesFromXml(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LigaDaJustica.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            LigaDaJustica liga = (LigaDaJustica) unmarshaller.unmarshal(new StringReader(xml));
            return liga.getCodinomes();
        } catch (JAXBException e) {
            throw new RuntimeException("Erro ao processar XML", e);
        }
    }


    // Classe auxiliar para mapear XML
    @XmlRootElement(name = "liga_da_justica")
    public static class LigaDaJustica {
        @XmlElementWrapper(name = "codinomes")
        @XmlElement(name = "codinome")
        private List<String> codinomes;

        public List<String> getCodinomes() {
            return codinomes;
        }
    }

}
