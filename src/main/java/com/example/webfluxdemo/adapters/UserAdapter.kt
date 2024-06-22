package com.example.webfluxdemo.adapters

import com.example.webfluxdemo.AbstractAdapter
import com.example.webfluxdemo.model.User
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

private const val URL_PREFIX: String = "http://localhost:8040/api/users/"

@Component
class UserAdapter(webClient: WebClient = WebClient.builder().build()) : AbstractAdapter(webClient, URL_PREFIX) {
    suspend fun getUser(userId: Int): User =
            sendRequest(userId.toString())
}