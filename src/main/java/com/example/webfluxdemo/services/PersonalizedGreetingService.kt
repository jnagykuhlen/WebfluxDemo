package com.example.webfluxdemo.services

import com.example.webfluxdemo.adapters.TranslationAdapter
import com.example.webfluxdemo.adapters.UserAdapter
import com.example.webfluxdemo.model.PersonalizedGreeting
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

private const val GREETING_WORD: String = "hello"

@Component
class PersonalizedGreetingService(private val userAdapter: UserAdapter,
                                  private val translationAdapter: TranslationAdapter) {
    suspend fun personalizeGreeting(userId: Int): PersonalizedGreeting {
        val user = userAdapter.getUser(userId)
        val greeting = translationAdapter.getTranslation(GREETING_WORD, user.language)
        return PersonalizedGreeting(greeting, user.name)
    }
}