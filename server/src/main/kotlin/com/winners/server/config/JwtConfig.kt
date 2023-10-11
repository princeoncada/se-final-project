package com.winners.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

@Configuration
class JwtConfig {
    @Bean
    fun secretKey(): SecretKey {
        val keyGenerator: KeyGenerator = KeyGenerator.getInstance("HmacSHA256")
        return keyGenerator.generateKey()
    }
}