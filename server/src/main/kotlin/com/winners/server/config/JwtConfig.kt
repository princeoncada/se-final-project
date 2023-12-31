package com.winners.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

@Configuration
class JwtConfig {
    @Bean
    fun secretKey(): SecretKey {
        val keyGenerator: KeyGenerator = KeyGenerator.getInstance("HmacSHA256")
        val key = keyGenerator.generateKey()
        println(key)
        return key
    }
}