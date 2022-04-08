package com.example.webfluxdemo

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

abstract class AbstractAdapter(protected val webClient: WebClient,
                               protected val urlPrefix: String) {

    class NetworkException(message: String) : RuntimeException(message)

    protected suspend inline fun <reified T> sendRequest(urlSuffix: String): T {
        val url = urlPrefix + urlSuffix
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError) {
                    val message = "Received status <" + it.statusCode().reasonPhrase + "> for URL: " + url
                    Mono.error(NetworkException(message))
                }
                .bodyToMono(T::class.java)
                .awaitSingle()
    }
}