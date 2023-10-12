package com.winners.server.application.rest

import com.winners.server.config.service.JwtService
import com.winners.server.config.service.StockUserDetailsService
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtService: JwtService,
    private val stockUserDetailsService: StockUserDetailsService
) {
    @GetMapping
    fun provideAccessToken(): RedirectView {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = stockUserDetailsService.loadUserByUsername(authentication.name)
        val token = jwtService.generateToken(user)
        println(token)
        return RedirectView("http://localhost:5000/retrieve-token?token=$token")
    }

    @GetMapping("/user")
    fun jwtCookie(request: HttpServletRequest): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val header: String = request.getHeader("Authorization")
        val token: String = header.substring(7)
        val payload: Claims = jwtService.getClaims(token)

        println(payload.subject)
        println(payload["authorities"])
        println(authentication.name)
        println(authentication.authorities)

        return "done"
    }
}