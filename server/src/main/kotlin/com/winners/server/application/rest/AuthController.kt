package com.winners.server.application.rest

import com.winners.server.config.service.JwtService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtService: JwtService
) {
    @GetMapping
    fun redirect(authentication: Authentication): RedirectView {
        println(authentication.name)
        return RedirectView("http://localhost:5173/login-success")
    }

    @GetMapping("/user")
    fun authenticateUser(): String {
        return "goods"
    }
}