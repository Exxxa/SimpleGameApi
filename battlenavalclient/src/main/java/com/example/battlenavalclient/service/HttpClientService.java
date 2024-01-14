package com.example.battlenavalclient.service;

// HttpClientService.java

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 * This class provides a simple HTTP client service using Spring's RestTemplate.
 */
public class HttpClientService {

    /*
     * The RestTemplate instance used to send HTTP requests.
     */
    private final RestTemplate restTemplate;

    /*
     * Constructs a new HttpClientService instance with a new RestTemplate.
     */
    public HttpClientService() {
        this.restTemplate = new RestTemplate();
    }

    /*
     * Sends an HTTP request to the specified URL using the given method and request entity,
     * and returns the response as a ResponseEntity of the specified response type.
     *
     * @param url           the URL to send the request to
     * @param method        the HTTP method to use (GET, POST, PUT, DELETE, etc.)
     * @param requestEntity the request entity (optional; may be null)
     * @param responseType  the class type of the expected response body
     * @param <T>           the type of the expected response body
     * @return the ResponseEntity containing the response body, headers, and status code
     */
    public <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) {
        // Use the RestTemplate to send the HTTP request and get the ResponseEntity
        return restTemplate.exchange(url, method, requestEntity, responseType);
    }
}