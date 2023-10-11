package com.mcm.winners.stockanalysis.domain.service

import com.mcm.winners.stockanalysis.application.dto.RoleDTOs
import com.mcm.winners.stockanalysis.domain.service.base.EntityService
import org.springframework.stereotype.Service

@Service
interface RoleService: EntityService<RoleDTOs.GetResult, RoleDTOs.PostRequest, RoleDTOs.PutRequest> {
}
