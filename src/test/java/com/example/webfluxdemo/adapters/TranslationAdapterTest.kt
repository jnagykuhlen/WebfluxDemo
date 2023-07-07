package com.example.webfluxdemo.adapters

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class TranslationAdapterTest {

    private val exchangeFunction = mockk<ExchangeFunction>()

    private val webClient = WebClient.builder()
            .exchangeFunction(exchangeFunction)
            .build()

    private val translationAdapter = TranslationAdapter(webClient)

    @Test
    fun testGetTranslationOk() {
        every { exchangeFunction.exchange(any()) } returns
                Mono.just(ClientResponse.create(HttpStatus.OK)
                        .body("Hallo")
                        .build())

        val actual = runBlocking {
            translationAdapter.getTranslation("hello", "de-DE")
        }

        assertThat(actual).isEqualTo("Hallo")
    }

    @Test
    fun testGetTranslationNotFound() {
        every { exchangeFunction.exchange(any()) } returns
                Mono.just(ClientResponse.create(HttpStatus.NOT_FOUND).build())

        assertThatThrownBy {
            runBlocking {
                translationAdapter.getTranslation("hello", "it-IT")
            }
        }.hasMessage("Received status <404 NOT_FOUND> for URL: http://localhost:8040/api/translations/hello/it-IT")
    }
}