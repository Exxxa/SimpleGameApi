package com.example.battlenavalclient;

import com.example.battlenavalclient.service.HttpClientService;
import com.example.battlenavalclient.service.Game;
import com.example.battlenavalclient.service.ShotResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
//import java.util.concurrent.Executors;

@SpringBootTest(classes = HttpClientService.class)
class HttpClientServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private FileWriter fileWriter;

    @Test
    void run() throws Exception {
        // Arrange
        HttpClientService httpClientService = new HttpClientService();
        httpClientService.setShipCount(10);
        httpClientService.setShotFired(0);

        // Mock responses from the external services
        Game game = new Game();
        game.setId("TestId");
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.eq(Game.class)))
                .thenReturn(Mockito.mock(ResponseEntity.class));
        Mockito.when(restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(ShotResult.class)))
                .thenReturn(ShotResult.MISS); // Adjust based on your actual ShotResult values

        // Mock the file writer behavior
        Mockito.doNothing().when(fileWriter).write(Mockito.anyString());

        // Act
        CommandLineRunner commandLineRunner = httpClientService.run(restTemplate);
        commandLineRunner.run();

        // Assert
        // Add assertions based on your expected behavior, for example:
        // Check that restTemplate methods were called the expected number of times
        Mockito.verify(restTemplate, Mockito.times(1))
                .postForEntity(Mockito.anyString(), Mockito.any(), Mockito.eq(Game.class));
        Mockito.verify(restTemplate, Mockito.times(100)) // Assuming a 10x10 grid
                .postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(ShotResult.class));
        // Check that fileWriter methods were called the expected number of times
        Mockito.verify(fileWriter, Mockito.times(1)).write(Mockito.anyString());
        Mockito.verify(fileWriter, Mockito.times(1)).close();
    }
}
