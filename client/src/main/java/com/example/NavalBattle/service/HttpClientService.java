package com.example.NavalBattle.service;

// HttpClientService.java
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpClientService {
    private final RestTemplate restTemplate;

    public HttpClientService() {
        this.restTemplate = new RestTemplate();
    }

    public <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) {
        return restTemplate.exchange(url, method, requestEntity, responseType);
    }

    // Additional methods for handling HTTP requests can be added here
}
