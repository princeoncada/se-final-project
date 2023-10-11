package com.mcm.winners.stockanalysis.config.service

import com.mcm.winners.stockanalysis.domain.repository.UserRepository
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