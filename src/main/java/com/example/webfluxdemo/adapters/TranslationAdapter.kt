package com.example.webfluxdemo.adapters

import com.example.webfluxdemo.AbstractAdapter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

private const val URL_PREFIX: String = "http://localhost:8040/api/translations/"

@Component
class TranslationAdapter(webClient: WebClient = WebClient.builder().build()) : AbstractAdapter(webClient, URL_PREFIX) {
    suspend fun getTranslation(word: String, language: String): String =
            sendRequest("$word/$language")
}