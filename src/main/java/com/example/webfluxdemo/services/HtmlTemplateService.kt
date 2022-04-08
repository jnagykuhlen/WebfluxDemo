package com.example.webfluxdemo.services

import org.springframework.stereotype.Component

@Component
class HtmlTemplateService {
    fun createHtml(greeting: String, name: String): String =
            "<body><h1>$greeting <i>$name</i>!</h1></body>"

    fun createErrorHtml(errorMessage: String): String =
            "<body><h1 style=\"color:red\">$errorMessage</h1></body>"
}