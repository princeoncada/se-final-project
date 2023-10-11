package com.winners.server.domain.service

import com.winners.server.application.dto.RefreshTokenDTOs
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface RefreshTokenService {
    fun getEntities(
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult>

    fun getEntityById(
        id: String,
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult>

    fun createEntity(
        entityRequest: RefreshTokenDTOs.PostRequest,
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult>

    fun deleteEntityById(
        id: String,
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult>
}