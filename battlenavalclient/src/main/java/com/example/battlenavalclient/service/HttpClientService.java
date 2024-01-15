package com.example.battlenavalclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
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
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			String quote = restTemplate.getForObject(
					"http://localhost:8080/game/", String.class);
			log.info(quote.toString());
		};
	}
}
