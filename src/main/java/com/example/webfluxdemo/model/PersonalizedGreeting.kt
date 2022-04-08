package com.example.webfluxdemo.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PersonalizedGreeting(@JsonProperty("greeting") val greeting: String,
                                @JsonProperty("name") val name: String)