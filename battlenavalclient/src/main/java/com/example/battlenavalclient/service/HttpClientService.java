package com.example.battlenavalclient.service;

import java.io.FileWriter;
import java.io.FilterWriter;
import java.util.concurrent.ExecutionException;

import org.apache.catalina.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HttpClientService {

	private int shipCount = 10;
	private int shotFired = 0; 

	private static final Logger log = LoggerFactory.getLogger(HttpClientService.class);

	public static void main(String[] args) {
		SpringApplication.run(HttpClientService.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ResponseEntity<Game> gameId = restTemplate.postForEntity("http://localhost:8080/game/start?teamName=Debugging-gorillas&gameId=1",null,Game.class);
			log.info("Response: {}", gameId);
			log.info("Response Body: {}", gameId.getBody());
			log.info("Response id: {}", 1);
			 
			FileWriter myObj = new FileWriter(
				"C:\\Users\\zmoy0\\Desktop\\Courses\\4A\\Application Architecture\\group-project\\battlenavalclient\\gameReviews\\debugging-gorillas"
				+ gameId.getBody() + ".txt");

			String baseUrls = "";
			for (int i=0; i<10; i++){
				for (int j = 0; j<10; j++){
					shotFired++;
					ShotResult cell = restTemplate.postForObject("http://localhost:8080/game/1/fire?lign=" + i + "&column=" + j, null, ShotResult.class);
					log.info(cell.toString());
					if(cell.toString() == "SUNK"){
						shipCount--;
					}
					log.info("Shots fired : {}", shotFired);
				}
			}
			myObj.write(shotFired);
		};
	}
}