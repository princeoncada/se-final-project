package com.winners.server.config.service

import com.winners.server.domain.model.User
import com.winners.server.domain.repository.RoleRepository
import com.winners.server.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class StockUserDetailsService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String): StockUserDetails {
        try {
            val user = userRepository.findByEmail(email)
            if (user.isEmpty) throw UsernameNotFoundException("User not found with email: $email")
            return StockUserDetails(user.get())
        } catch (e: UsernameNotFoundException) {
            val savedUser = userRepository.save(
                User(
                    id = UUID.randomUUID().toString(),
                    role = roleRepository.findByName("USER").get(),
                    email = email,
                    password = "",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now()
                )
            )
            return StockUserDetails(savedUser)
        }
    }
}