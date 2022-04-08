package com.example.webfluxdemo.adapters;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TranslationAdapterTest {

    private final RestTemplate restTemplate = mock(RestTemplate.class);
    private final TranslationAdapter translationAdapter = new TranslationAdapter(restTemplate);

    @Test
    void testGetTranslationOk() {
        when(restTemplate.getForEntity("http://localhost:8040/api/translations/hello/de-DE", String.class)).thenReturn(
                new ResponseEntity<>("Hallo", HttpStatus.OK)
        );

        assertThat(translationAdapter.getTranslation("hello", "de-DE")).isEqualTo("Hallo");
    }

    @Test
    void testGetTranslationNotFound() {
        when(restTemplate.getForEntity("http://localhost:8040/api/translations/hello/it-IT", String.class)).thenThrow(
                new HttpClientErrorException(HttpStatus.NOT_FOUND)
        );

        assertThatThrownBy(() -> translationAdapter.getTranslation("hello", "it-IT"))
                .hasMessage("Received status <NOT_FOUND> for URL: http://localhost:8040/api/translations/hello/it-IT");
    }
}