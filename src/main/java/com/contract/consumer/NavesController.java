package com.contract.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NavesController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);
    private static final String STARSHIPS_ENDPOINT = "http://localhost:8080/starship?id=";

    @GetMapping("/nave")
    public Nave getNave(@RequestParam(value = "id") Long id) {
        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Nave> responseEntity = restTemplate.getForEntity(STARSHIPS_ENDPOINT + id.toString(), Nave.class);
        return responseEntity.getBody();
    }
}
