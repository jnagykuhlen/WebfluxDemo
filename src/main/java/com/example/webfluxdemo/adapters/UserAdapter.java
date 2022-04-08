package com.example.webfluxdemo.adapters;

import com.example.webfluxdemo.AbstractAdapter;
import com.example.webfluxdemo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAdapter extends AbstractAdapter<User> {

    private static final String URL_PREFIX = "http://localhost:8040/api/users/";

    public UserAdapter() {
        this(new RestTemplate());
    }

    public UserAdapter(RestTemplate restTemplate) {
        super(restTemplate, URL_PREFIX);
    }

    public User getUser(int userId) {
        return sendRequest(Integer.toString(userId), User.class);
    }

}
