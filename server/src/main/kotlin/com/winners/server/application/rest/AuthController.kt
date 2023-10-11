package com.winners.server.application.rest

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {
    @GetMapping("/oauth2/callback")
    fun authenticateUser(authentication: Authentication): String {
        return authentication.toString()
    }
}