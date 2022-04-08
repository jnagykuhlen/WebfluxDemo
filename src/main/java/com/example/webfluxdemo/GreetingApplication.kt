package com.example.webfluxdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<GreetingApplication>(*args)
}

@SpringBootApplication
open class GreetingApplication