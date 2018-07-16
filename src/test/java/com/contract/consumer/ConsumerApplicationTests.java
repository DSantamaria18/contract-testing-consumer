package com.contract;
package com.contract.consumer;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTests {

    private static final String STARSHIPS_ENDPOINT = "http://localhost:8080/starship?id=10";

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadLatestStub("com.contract", "provider", "stubs")
            .withPort(6060)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Test
    public void getNaveFromProvider() {
        // Given:
        RestTemplate restTemplate = new RestTemplate();

        // When:
        ResponseEntity responseEntity = restTemplate.getForEntity(STARSHIPS_ENDPOINT,  Object.class);

        // Then:
        BDDAssertions.then(responseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(responseEntity.getBody().get)
    }

}
