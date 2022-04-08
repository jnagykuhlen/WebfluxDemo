package com.example.webfluxdemo.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HtmlTemplateServiceTest {

    private final HtmlTemplateService htmlTemplateService = new HtmlTemplateService();

    @Test
    void testCreateHtml() {
        String result = htmlTemplateService.createHtml("Hello", "World");
        assertThat(result).isEqualTo("<body><h1>Hello <i>World</i>!</h1></body>");
    }

    @Test
    void testCreateErrorHtml() {
        String result = htmlTemplateService.createErrorHtml("I am Error");
        assertThat(result).isEqualTo("<body><h1 style=\"color:red\">I am Error</h1></body>");
    }
}