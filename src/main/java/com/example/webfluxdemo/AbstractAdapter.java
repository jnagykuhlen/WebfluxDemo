package com.example.webfluxdemo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public abstract class AbstractAdapter<T> {

    public static class NetworkException extends RuntimeException {
        public NetworkException(String message) {
            super(message);
        }
    }

    private final WebClient webClient;
    private final String urlPrefix;

    protected T sendRequest(String urlSuffix, Class<T> responseClass) {
        String url = urlPrefix + urlSuffix;
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    String message = "Received status <" + response.statusCode().getReasonPhrase() + "> for URL: " + url;
                    return Mono.error(new NetworkException(message));
                })
                .bodyToMono(responseClass)
                .block();
    }

}
