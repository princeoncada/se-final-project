package com.mcm.winners.stockanalysis.domain.repository

import com.mcm.winners.stockanalysis.domain.model.Role
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, String> {
    fun findById(id: String, pageable: Pageable): Page<Role>
}