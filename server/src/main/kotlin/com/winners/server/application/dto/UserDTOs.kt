package com.winners.server.application.dto

class UserDTOs {
    data class GetResult(
        val id: String,
        val role: String,
        val email: String,
        val password: String,
        val firstName: String?,
        val lastName: String?,
        val birthDate: String?,
    )

    data class PostRequest(
        val role: String,
        val email: String,
        val password: String,
        val firstName: String?,
        val lastName: String?,
        val birthDate: String?,
    )

    data class PutRequest(
        val role: String?,
        val email: String?,
        val password: String?,
        val firstName: String?,
        val lastName: String?,
        val birthDate: String?,
    )
}