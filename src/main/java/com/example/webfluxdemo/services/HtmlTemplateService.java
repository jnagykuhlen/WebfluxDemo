package com.example.webfluxdemo.services;

import org.springframework.stereotype.Component;

@Component
public class HtmlTemplateService {

    public String createHtml(String greeting, String name) {
        return "<body><h1>" + greeting + " <i>" + name + "</i>!</h1></body>";
    }

    public String createErrorHtml(String errorMessage) {
        return "<body><h1 style=\"color:red\">" + errorMessage + "</h1></body>";
    }

}
