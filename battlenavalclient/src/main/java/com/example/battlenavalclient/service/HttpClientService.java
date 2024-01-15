package com.example.battlenavalclient.service;

import org.apache.catalina.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HttpClientService {

	private static final Logger log = LoggerFactory.getLogger(HttpClientService.class);

	public static void main(String[] args) {
		SpringApplication.run(HttpClientService.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	@PostMapping("/game/start")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Game quote = restTemplate.postForObject(
					"http://localhost:8080/game/start/", "myTeam",Game.class);
			log.info(quote.toString());
		};
	}
}
