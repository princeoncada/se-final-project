package com.winners.server.application.dto

class RefreshTokenDTOs {
    data class GetResult(
        val id: String,
        val user: String,
        val refreshToken: String,
        val expirationDate: String
    )

    data class PostRequest(
        val userId: String,
        val refreshToken: String,
        val expirationDate: String
    )
}