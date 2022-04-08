package com.example.webfluxdemo;

import com.example.webfluxdemo.model.PersonalizedGreeting;
import com.example.webfluxdemo.services.HtmlTemplateService;
import com.example.webfluxdemo.services.PersonalizedGreetingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GreetingController {

    @Autowired
    private final PersonalizedGreetingService personalizedGreetingService;

    @Autowired
    private final HtmlTemplateService htmlTemplateService;

    @GetMapping(value = "/hello/{userId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getHello(@PathVariable int userId) {
        try {
            PersonalizedGreeting personalizedGreeting = personalizedGreetingService.personalizeGreeting(userId).block();
            return htmlTemplateService.createHtml(
                    personalizedGreeting.getGreeting(),
                    personalizedGreeting.getName()
            );
        } catch(Exception exception) {
            System.out.println("EXCEPTION: " + exception.getMessage());
            String errorMessage = "Cannot personalize greeting for user with ID " + userId + "!";
            return htmlTemplateService.createErrorHtml(errorMessage);
        }
    }

}
