package com.winners.server.service

import com.winners.server.application.dto.RefreshTokenDTOs
import com.winners.server.application.mapper.RefreshTokenMapper
import com.winners.server.domain.repository.RefreshTokenRepository
import com.winners.server.domain.service.RefreshTokenService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
): RefreshTokenService {
    override fun getEntities(
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult> {
        return refreshTokenRepository.findAll(pageable).map { refreshTokenMapper.toGetResult(it) }
    }

    override fun getEntityById(
        id: String,
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult> {
        val entity = refreshTokenRepository.findById(id, pageable)
        return entity.map { refreshTokenMapper.toGetResult(it) }
    }

    override fun createEntity(
        entityRequest: RefreshTokenDTOs.PostRequest,
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult> {
        val entityId = UUID.randomUUID().toString()
        val savedEntity = refreshTokenRepository.save(refreshTokenMapper.createEntity(entityId, entityRequest))
        return refreshTokenRepository.findById(savedEntity.id, pageable).map { refreshTokenMapper.toGetResult(it) }
    }

    override fun deleteEntityById(
        id: String,
        pageable: Pageable
    ): Page<RefreshTokenDTOs.GetResult> {
        val currentEntity = refreshTokenRepository.findById(id).get()
        refreshTokenRepository.delete(currentEntity)
        return refreshTokenRepository.findAll(pageable).map { refreshTokenMapper.toGetResult(it) }
    }
}