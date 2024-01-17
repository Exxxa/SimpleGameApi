// This class represents an HTTP client service for a naval battle game
package com.example.battlenavalclient.service;

import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HttpClientService {

	public void setShipCount(int i) {
		shipCount = i;
	}

	public void setShotFired(int i) {
		shotFired = i;
	}

    // Variables to keep track of ship count and shots fired


    private int shipCount = 10;
    private int shotFired = 0;

    // Logger for logging messages
    private static final Logger log = LoggerFactory.getLogger(HttpClientService.class);

    // Main method to run the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(HttpClientService.class, args);
    }

    // Bean to create and configure a RestTemplate
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // CommandLineRunner bean to perform actions when the application starts
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            // Start a new game and retrieve the game ID
            ResponseEntity<Game> gameId = restTemplate.postForEntity(
                "http://localhost:8080/game/start?teamName=Debugging-gorillas&gameId=1", null, Game.class);
            log.info("Response: {}", gameId);
            log.info("Response Body: {}", gameId.getBody());
            log.info("Response id: {}", 1);

            // Create a FileWriter for writing game review data to a file
            FileWriter myObj = new FileWriter("gameReviews\\debugging-gorillas"
                + gameId.getBody() + ".txt", false);

            // Loop through each cell on the game grid and fire shots
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    shotFired++;
                    // Fire a shot and retrieve the result
                    ShotResult cell = restTemplate.postForObject(
                        "http://localhost:8080/game/1/fire?lign=" + i + "&column=" + j, null, ShotResult.class);
                    log.info(cell.toString());
                    log.info("Shots fired : {}", shotFired);
                    
                    // Update ship count if a ship is sunk
                    if (cell == ShotResult.SUNK) {
                        shipCount--;
                    }

                    // Check if all ships are sunk
                    if (shipCount == 0) {
                        String shot = "Number of shots fired : " + shotFired;
                        myObj.write(shot);
                        break;
                    }
                }

                // Break the outer loop if all ships are sunk
                if (shipCount == 0) {
                    break;
                }
            }

            // Close the FileWriter
            myObj.close();
        };
    }
}
