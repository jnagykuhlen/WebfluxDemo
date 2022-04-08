package com.example.webfluxdemo.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HtmlTemplateServiceTest {

    private val htmlTemplateService = HtmlTemplateService()

    @Test
    fun testCreateHtml() {
        val result = htmlTemplateService.createHtml("Hello", "World")
        assertThat(result).isEqualTo("<body><h1>Hello <i>World</i>!</h1></body>")
    }

    @Test
    fun testCreateErrorHtml() {
        val result = htmlTemplateService.createErrorHtml("I am Error")
        assertThat(result).isEqualTo("<body><h1 style=\"color:red\">I am Error</h1></body>")
    }
}