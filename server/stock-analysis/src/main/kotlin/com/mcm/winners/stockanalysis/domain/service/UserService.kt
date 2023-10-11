package com.mcm.winners.stockanalysis.domain.service

import com.mcm.winners.stockanalysis.application.dto.UserDTOs
import com.mcm.winners.stockanalysis.domain.service.base.EntityService
import org.springframework.stereotype.Service

@Service
interface UserService: EntityService<UserDTOs.GetResult, UserDTOs.PostRequest, UserDTOs.PutRequest> {
}