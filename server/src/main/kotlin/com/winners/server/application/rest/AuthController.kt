package com.winners.server.application.rest

import com.nimbusds.oauth2.sdk.TokenResponse
import com.winners.server.config.service.JwtService
import com.winners.server.config.service.StockUserDetailsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.view.RedirectView
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtService: JwtService,
    private val stockUserDetailsService: StockUserDetailsService,
    private val clientRegistrationRepository: ClientRegistrationRepository,
    private val restTemplate: RestTemplate
) {
    @GetMapping
    fun redirect(authentication: Authentication): RedirectView {
        println(authentication.name)
        val user = stockUserDetailsService.loadUserByUsername(authentication.name)
        val token = jwtService.generateToken(user)
        println(token)
        return RedirectView("http://localhost:5000/retrieve-token?token=$token")
//        return RedirectView("http://localhost:8080/api/auth/user")
    }

    @GetMapping("/user")
    fun getUser(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }
//
//    private val registrationId: String = "google"
//
//    data class CodeRequest(
//        val code: String?
//    )
//
//    @PostMapping("/google/exchange")
//    fun exchangeAuthorizationCode(@RequestBody codeRequest: CodeRequest) {
//        println(codeRequest)
//    }
}