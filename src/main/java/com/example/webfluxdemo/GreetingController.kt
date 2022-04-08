package com.example.webfluxdemo

import com.example.webfluxdemo.services.HtmlTemplateService
import com.example.webfluxdemo.services.PersonalizedGreetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class GreetingController(@Autowired private val personalizedGreetingService: PersonalizedGreetingService,
                         @Autowired private val htmlTemplateService: HtmlTemplateService) {

    @GetMapping("/hello/{userId}", produces = [MediaType.TEXT_HTML_VALUE])
    fun getHello(@PathVariable userId: Int): Mono<String> =
            personalizedGreetingService.personalizeGreeting(userId)
                    .map {
                        htmlTemplateService.createHtml(
                                it.greeting,
                                it.name
                        )
                    }
                    .onErrorResume {
                        println("EXCEPTION: " + it.message)
                        val errorMessage = "Cannot personalize greeting for user with ID $userId!"
                        Mono.just(htmlTemplateService.createErrorHtml(errorMessage))
                    }
}