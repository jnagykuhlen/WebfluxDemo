package com.example.webfluxdemo.services;

import com.example.webfluxdemo.adapters.TranslationAdapter;
import com.example.webfluxdemo.adapters.UserAdapter;
import com.example.webfluxdemo.model.PersonalizedGreeting;
import com.example.webfluxdemo.model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonalizedGreetingServiceTest {

    private final UserAdapter userAdapter = mock(UserAdapter.class);
    private final TranslationAdapter translationAdapter = mock(TranslationAdapter.class);

    private final PersonalizedGreetingService personalizedGreetingService = new PersonalizedGreetingService(
            userAdapter,
            translationAdapter
    );

    @Test
    void testPersonalizeGreeting() {
        when(userAdapter.getUser(123)).thenReturn(new User("Francois", "fr-FR"));
        when(translationAdapter.getTranslation("hello", "fr-FR")).thenReturn("Bonjour");

        PersonalizedGreeting actual = personalizedGreetingService.personalizeGreeting(123);

        PersonalizedGreeting expected = new PersonalizedGreeting("Bonjour", "Francois");
        assertThat(actual).isEqualTo(expected);
    }
}