package com.example.webfluxdemo.services;

import com.example.webfluxdemo.adapters.TranslationAdapter;
import com.example.webfluxdemo.adapters.UserAdapter;
import com.example.webfluxdemo.model.PersonalizedGreeting;
import com.example.webfluxdemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonalizedGreetingService {

    private static final String GREETING_WORD = "hello";

    private final UserAdapter userAdapter;
    private final TranslationAdapter translationAdapter;

    public PersonalizedGreeting personalizeGreeting(int userId) {
        User user = userAdapter.getUser(userId);
        String greeting = translationAdapter.getTranslation(GREETING_WORD, user.getLanguage());

        return new PersonalizedGreeting(greeting, user.getName());
    }

}
