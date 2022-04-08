package com.example.webfluxdemo.services

import com.example.webfluxdemo.adapters.TranslationAdapter
import com.example.webfluxdemo.adapters.UserAdapter
import com.example.webfluxdemo.model.PersonalizedGreeting
import com.example.webfluxdemo.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class PersonalizedGreetingServiceTest {

    private val userAdapter = mockk<UserAdapter>()
    private val translationAdapter = mockk<TranslationAdapter>()

    private val personalizedGreetingService = PersonalizedGreetingService(
            userAdapter,
            translationAdapter
    )

    @Test
    fun testPersonalizeGreeting() {
        coEvery { userAdapter.getUser(123) } returns User("Francois", "fr-FR")
        coEvery { translationAdapter.getTranslation("hello", "fr-FR") } returns "Bonjour"

        val actual = runBlocking {
            personalizedGreetingService.personalizeGreeting(123)
        }

        assertThat(actual).isEqualTo(PersonalizedGreeting("Bonjour", "Francois"))
    }
}