package com.winners.server.application.rest

import com.winners.server.application.dto.RefreshTokenDTOs
import com.winners.server.domain.service.RefreshTokenService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/refresh-tokens")
class RefreshTokenController(
    private val refreshTokenService: RefreshTokenService
) {
    @GetMapping
    fun getEntities(
        pageable: Pageable
    ): ResponseEntity<Page<RefreshTokenDTOs.GetResult>> {
        return try {
            val refreshToken = refreshTokenService.getEntities(pageable)
            ResponseEntity.ok(refreshToken)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}")
    fun getEntityById(
        @PathVariable id: String,
        pageable: Pageable
    ): ResponseEntity<Page<RefreshTokenDTOs.GetResult>> {
        return try {
            val refreshToken = refreshTokenService.getEntityById(id, pageable)
            ResponseEntity.ok(refreshToken)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteEntityById(
        @PathVariable id: String,
        pageable: Pageable
    ): ResponseEntity<Page<RefreshTokenDTOs.GetResult>> {
        return try {
            val refreshToken = refreshTokenService.deleteEntityById(id, pageable)
            ResponseEntity.ok(refreshToken)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createEntity(
        @RequestBody entityRequest: RefreshTokenDTOs.PostRequest,
        pageable: Pageable
    ): ResponseEntity<Page<RefreshTokenDTOs.GetResult>> {
        return try {
            val refreshToken = refreshTokenService.createEntity(entityRequest, pageable)
            ResponseEntity.ok(refreshToken)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}