package com.example.webfluxdemo;

import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public abstract class AbstractAdapter<T> {

    public static class NetworkException extends RuntimeException {
        public NetworkException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private final RestTemplate restTemplate;
    private final String urlPrefix;

    protected T sendRequest(String urlSuffix, Class<T> responseClass) {
        String url = urlPrefix + urlSuffix;
        try {
            return restTemplate.getForEntity(url, responseClass).getBody();
        } catch (HttpClientErrorException clientException) {
            String message = "Received status <" + clientException.getStatusText() + "> for URL: " + url;
            throw new NetworkException(message, clientException);
        }
    }

}
