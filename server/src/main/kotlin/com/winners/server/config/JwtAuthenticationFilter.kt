package com.winners.server.config

import com.winners.server.config.service.JwtService
import com.winners.server.config.service.StockUserDetailsService
import com.winners.server.domain.repository.RefreshTokenRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    /**
     * Filter the request
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Get X-Access-Token from the request header
        val header: String? = request.getHeader("Authorization")

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token: String = header.substring(7)
        val payload = jwtService.getClaims(token)

        // Get user details
        val userId = payload.subject
        val roles = payload["authorities"] as List<*>
        val authorities = roles.map { SimpleGrantedAuthority(it as String) }

        val authenticationToken = UsernamePasswordAuthenticationToken(
            userId,
            null,
            authorities
        )
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authenticationToken
        filterChain.doFilter(request, response)
    }
}