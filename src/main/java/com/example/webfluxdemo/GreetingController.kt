package com.example.webfluxdemo

import com.example.webfluxdemo.services.HtmlTemplateService
import com.example.webfluxdemo.services.PersonalizedGreetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(@Autowired private val personalizedGreetingService: PersonalizedGreetingService,
                         @Autowired private val htmlTemplateService: HtmlTemplateService) {

    @GetMapping("/hello/{userId}", produces = [MediaType.TEXT_HTML_VALUE])
    suspend fun getHello(@PathVariable userId: Int): String =
            try {
                val personalizedGreeting = personalizedGreetingService.personalizeGreeting(userId)
                htmlTemplateService.createHtml(
                        personalizedGreeting.greeting,
                        personalizedGreeting.name
                )
            } catch (exception: Exception) {
                println("EXCEPTION: " + exception.message)
                val errorMessage = "Cannot personalize greeting for user with ID $userId!"
                htmlTemplateService.createErrorHtml(errorMessage)
            }
}