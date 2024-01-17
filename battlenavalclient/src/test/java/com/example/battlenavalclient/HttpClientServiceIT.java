// src/test/java/com/example/battlenavalclient/service/HttpClientServiceIT.java

package com.example.battlenavalclient;

import com.example.battlenavalclient.service.Game;
import com.example.battlenavalclient.service.ShotResult;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpClientServiceIT {

    //@LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIntegrationWithServer() {
        
        String url = "http://localhost:" + port + "/game/start?teamName=IntegrationTestTeam&gameId=1";
        Game response = restTemplate.postForObject(url, null, Game.class);
        
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getTeamName()).isEqualTo("IntegrationTestTeam");
        
        url = "http://localhost:8080/game/1/fire?lign=" + 0 + "&column=" + 0;
        ShotResult shotResult = restTemplate.postForObject(url, null, ShotResult.class);
        assertThat(shotResult).isNotNull();
    }
}