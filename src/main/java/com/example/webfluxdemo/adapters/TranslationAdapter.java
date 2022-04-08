package com.example.webfluxdemo.adapters;

import com.example.webfluxdemo.AbstractAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TranslationAdapter extends AbstractAdapter<String> {

    private static final String URL_PREFIX = "http://localhost:8040/api/translations/";

    public TranslationAdapter() {
        this(new RestTemplate());
    }

    public TranslationAdapter(RestTemplate restTemplate) {
        super(restTemplate, URL_PREFIX);
    }

    public String getTranslation(String word, String language) {
        return sendRequest(word + "/" + language, String.class);
    }

}
