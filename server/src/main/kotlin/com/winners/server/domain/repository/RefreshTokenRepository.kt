package com.winners.server.domain.repository

import com.winners.server.domain.model.RefreshToken
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RefreshTokenRepository: JpaRepository<RefreshToken, String> {
    fun findById(id: String, pageable: Pageable): Page<RefreshToken>
    fun findByUserId(userId: String): Optional<RefreshToken>
}