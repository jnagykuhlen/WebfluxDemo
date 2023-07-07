package com.example.webfluxdemo.adapters;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TranslationAdapterTest {

    private final ExchangeFunction exchangeFunction = mock(ExchangeFunction.class);

    private final WebClient webClient = WebClient.builder()
            .exchangeFunction(exchangeFunction)
            .build();

    private final TranslationAdapter translationAdapter = new TranslationAdapter(webClient);

    @Test
    void testGetTranslationOk() {
        when(exchangeFunction.exchange(any())).thenReturn(Mono.just(
                ClientResponse.create(HttpStatus.OK)
                        .body("Hallo")
                        .build()
        ));

        assertThat(translationAdapter.getTranslation("hello", "de-DE").block()).isEqualTo("Hallo");
    }

    @Test
    void testGetTranslationNotFound() {
        when(exchangeFunction.exchange(any())).thenReturn(Mono.just(
                ClientResponse.create(HttpStatus.NOT_FOUND).build()
        ));

        assertThatThrownBy(() -> translationAdapter.getTranslation("hello", "it-IT").block())
                .hasMessage("Received status <404 NOT_FOUND> for URL: http://localhost:8040/api/translations/hello/it-IT");
    }
}