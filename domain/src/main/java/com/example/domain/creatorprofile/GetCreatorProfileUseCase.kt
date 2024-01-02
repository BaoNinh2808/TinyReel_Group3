package com.example.domain.creatorprofile

import com.example.data.model.UserModel
import com.example.data.repository.creatorprofile.CreatorProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCreatorProfileUseCase @Inject constructor(
    private val creatorProfileRepository: CreatorProfileRepository
) {
    operator fun invoke(id: Long): Flow<UserModel?> {
        return creatorProfileRepository.getCreatorDetails(id)
    }
}
