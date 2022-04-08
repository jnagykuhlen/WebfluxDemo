package com.example.webfluxdemo.services

import com.example.webfluxdemo.adapters.TranslationAdapter
import com.example.webfluxdemo.adapters.UserAdapter
import com.example.webfluxdemo.model.PersonalizedGreeting
import com.example.webfluxdemo.model.User
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

internal class PersonalizedGreetingServiceTest {

    private val userAdapter = mockk<UserAdapter>()
    private val translationAdapter = mockk<TranslationAdapter>()

    private val personalizedGreetingService = PersonalizedGreetingService(
            userAdapter,
            translationAdapter
    )

    @Test
    fun testPersonalizeGreeting() {
        every { userAdapter.getUser(123) } returns
                Mono.just(User("Francois", "fr-FR"))

        every { translationAdapter.getTranslation("hello", "fr-FR") } returns
                Mono.just("Bonjour")

        val actual = personalizedGreetingService.personalizeGreeting(123).block()
        val expected = PersonalizedGreeting("Bonjour", "Francois")
        assertThat(actual).isEqualTo(expected)
    }
}