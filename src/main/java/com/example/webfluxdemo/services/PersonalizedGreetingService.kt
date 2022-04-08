package com.example.webfluxdemo.services;

import com.example.webfluxdemo.adapters.TranslationAdapter;
import com.example.webfluxdemo.adapters.UserAdapter;
import com.example.webfluxdemo.model.PersonalizedGreeting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PersonalizedGreetingService {

    private static final String GREETING_WORD = "hello";

    private final UserAdapter userAdapter;
    private final TranslationAdapter translationAdapter;

    public Mono<PersonalizedGreeting> personalizeGreeting(int userId) {
        return userAdapter.getUser(userId)
                .flatMap(user -> translationAdapter.getTranslation(GREETING_WORD, user.getLanguage())
                        .map(greeting -> new PersonalizedGreeting(greeting, user.getName())));
    }

}
