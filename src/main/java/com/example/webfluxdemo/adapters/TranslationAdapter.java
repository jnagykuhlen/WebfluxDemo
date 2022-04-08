package com.example.webfluxdemo.adapters;

import com.example.webfluxdemo.AbstractAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TranslationAdapter extends AbstractAdapter<String> {

    private static final String URL_PREFIX = "http://localhost:8040/api/translations/";

    public TranslationAdapter() {
        this(WebClient.builder().build());
    }

    public TranslationAdapter(WebClient webClient) {
        super(webClient, URL_PREFIX);
    }

    public String getTranslation(String word, String language) {
        return sendRequest(word + "/" + language, String.class).block();
    }

}
