package com.example.battlenavalserver.config;

import com.example.battlenavalserver.repository.GameRepository;
import com.example.battlenavalserver.service.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GameRepository gameRepository() {
        return new GameRepository();
    }

    @Bean
    public GameService gameService(GameRepository gameRepository) {
        return new GameService(gameRepository);
    }
}
