package com.mcm.winners.stockanalysis.config.provider

import com.mcm.winners.stockanalysis.config.service.StockUserDetails
import com.mcm.winners.stockanalysis.config.service.StockUserDetailsService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class StockAuthenticationProvider(
    private val stockUserDetailsService: StockUserDetailsService
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        try {
            val email: String = authentication?.principal as String
            val password: String = authentication.credentials as String
            val userDetails: StockUserDetails = stockUserDetailsService.loadUserByUsername(email)
            return UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}