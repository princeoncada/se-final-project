package com.winners.server.application.mapper

import com.winners.server.application.dto.UserDTOs
import com.winners.server.application.mapper.base.EntityMapper
import com.winners.server.domain.model.User
import com.winners.server.domain.repository.RoleRepository
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime

@Component
class UserMapper(
    private val roleRepository: RoleRepository
): EntityMapper<User, UserDTOs.GetResult, UserDTOs.PostRequest, UserDTOs.PutRequest> {
    override fun toGetResult(
        entity: User
    ): UserDTOs.GetResult {
        return UserDTOs.GetResult(
            id = entity.id,
            role = entity.role.name,
            email = entity.email,
            password = entity.password,
            firstName = entity.firstName,
            lastName = entity.lastName,
            birthDate = entity.birthDate.toString()
        )
    }

    override fun createEntity(
        id: String,
        entityRequest: UserDTOs.PostRequest
    ): User {
        return User(
            id = id,
            role = roleRepository.findByName(entityRequest.role).get(),
            email = entityRequest.email,
            password = entityRequest.password,
            firstName = entityRequest.firstName,
            lastName = entityRequest.lastName,
            birthDate = LocalDateTime.parse(entityRequest.birthDate),
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )
    }

    override fun updateEntity(
        entity: User,
        entityRequest: UserDTOs.PutRequest
    ): User {
        return User(
            id = entity.id,
            role = roleRepository.findById(entityRequest.role ?: entity.role.id).get(),
            email = entityRequest.email ?: entity.email,
            password = entityRequest.password ?: entity.password,
            firstName = entityRequest.firstName ?: entity.firstName,
            lastName = entityRequest.lastName ?: entity.lastName,
            birthDate = entityRequest.birthDate?.let { LocalDateTime.parse(it) } ?: entity.birthDate,
            createdAt = entity.createdAt,
            updatedAt = Instant.now()
        )
    }
}