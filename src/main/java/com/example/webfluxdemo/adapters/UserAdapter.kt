package com.example.webfluxdemo.adapters;

import com.example.webfluxdemo.AbstractAdapter;
import com.example.webfluxdemo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserAdapter extends AbstractAdapter<User> {

    private static final String URL_PREFIX = "http://localhost:8040/api/users/";

    public UserAdapter() {
        this(WebClient.builder().build());
    }

    public UserAdapter(WebClient webClient) {
        super(webClient, URL_PREFIX);
    }

    public Mono<User> getUser(int userId) {
        return sendRequest(Integer.toString(userId), User.class);
    }

}
