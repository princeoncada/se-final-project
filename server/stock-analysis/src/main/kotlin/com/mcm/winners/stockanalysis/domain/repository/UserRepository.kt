package com.mcm.winners.stockanalysis.domain.repository

import com.mcm.winners.stockanalysis.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, String>{
    fun findById(id: String, pageable: Pageable): Page<User>
    fun findByEmail(email: String): Optional<User>
}