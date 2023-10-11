package com.winners.server.config.service

import com.winners.server.domain.repository.RefreshTokenRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import java.util.Date
import javax.crypto.SecretKey

class JwtService(
    private val secretKey: SecretKey,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun generateToken(principal: StockUserDetails): String {
        val now = Date()
        val validity = Date(now.time + ACCESS_TOKEN_VALIDITY)
        return Jwts.builder()
            .setIssuer("https://localhost:8080/authenticate")
            .setSubject(principal.username)
            .setAudience("authenticate-api")
            .setId(principal.getUserId())
            .setExpiration(validity)
            .setIssuedAt(now)
            .claim("authorities", principal.authorities.map { it.authority })
            .signWith(secretKey)
            .compact()
    }

    fun generateRefreshToken(principal: StockUserDetails): String {
        val now = Date()
        val validity = Date(now.time + REFRESH_TOKEN_VALIDITY)
        return Jwts.builder()
            .setIssuer("https://localhost:8080/authenticate")
            .setSubject(principal.username)
            .setAudience("authenticate-api")
            .setId(principal.getUserId())
            .setExpiration(validity)
            .setIssuedAt(now)
            .claim("authorities", principal.authorities.map { it.authority })
            .signWith(secretKey)
            .compact()
    }

    fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun isTokenExpired(token: String): Boolean {
        return getClaims(token).expiration.before(Date())
    }

    fun isTokenValid(token: String, userDetails: StockUserDetails): Boolean {
        return getClaims(token).subject == userDetails.username && !isTokenExpired(token)
    }

    fun createCookie(token: String = "", age: Int = 0): Cookie {
        return Cookie("token", token).apply {
            maxAge = age
            isHttpOnly = true
            path = "/"
            domain = "localhost"
        }
    }

    fun clearAccessTokenCookie(response: HttpServletResponse) {
       response.addCookie(createCookie(age=0))
    }

    fun revokeRefreshToken(userId: String) {
        val refreshToken = refreshTokenRepository.findByUserId(userId)
        if (refreshToken.isPresent) {
            refreshTokenRepository.delete(refreshToken.get())
        }
    }

    companion object {
        private const val ACCESS_TOKEN_VALIDITY = 3600000L // Token validity: 1 hour
        private const val REFRESH_TOKEN_VALIDITY = 86400000L // Token validity: 1 day
    }
}


