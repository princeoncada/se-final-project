package com.winners.server.config.service

import com.winners.server.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class StockUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String): StockUserDetails {
        val user = userRepository.findByEmail(email)
        if (user.isEmpty) throw UsernameNotFoundException("User not found with email: $email")
        return StockUserDetails(user.get())
    }
}