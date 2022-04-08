package com.example.webfluxdemo.model

import com.fasterxml.jackson.annotation.JsonProperty

data class User(@JsonProperty("name") val name: String,
                @JsonProperty("language") val language: String)