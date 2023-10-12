package com.winners.server.config

import com.winners.server.config.service.JwtService
import com.winners.server.config.service.StockUserDetails
import com.winners.server.config.service.StockUserDetailsService
import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.JwtValidationException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(1)
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val stockUserDetailsService: StockUserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            var jwtToken: String? = null
            val cookies: Array<Cookie>? = request.cookies
            if (cookies != null) {
                for (cookie in cookies) {
                    if (cookie.name == "JWT") {
                        jwtToken = cookie.value
                    }
                }
            }

            if (jwtToken == null) {
                val authorizationHeader: String? = request.getHeader("Authorization")
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    jwtToken = authorizationHeader.substring(7)
                } else {
                    filterChain.doFilter(request, response)
                    return
                }
            }

            val payload: Claims = jwtService.getClaims(jwtToken)
            val userDetails: StockUserDetails = stockUserDetailsService.loadUserByUsername(payload.subject)
            if (!jwtService.isTokenValid(jwtToken, userDetails)) throw JwtValidationException("Invalid token", null)

            val roles = payload["authorities"] as List<*>
            val authorities = roles.map { SimpleGrantedAuthority(it as String) }
            val authenticationToken = UsernamePasswordAuthenticationToken(
                userDetails.username,
                null,
                authorities
            )

            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authenticationToken

            println("Filter JWT: $jwtToken")
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            println(e)
            response.sendRedirect("http://localhost:5000/logout")
        }
    }
}