package com.mcm.winners.stockanalysis.application.dto

class RoleDTOs {
    data class GetResult(
        val id: String,
        val name: String
    )

    data class PostRequest(
        val name: String
    )

    data class PutRequest(
        val name: String?
    )
}