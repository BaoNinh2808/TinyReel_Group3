package com.example.domain.creatorprofile

import com.example.data.model.UserModel
import com.example.data.repository.creatorprofile.CreatorProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditableCreatorProfileUseCase @Inject constructor(
    private val creatorProfileRepository: CreatorProfileRepository
) {
    private var _id: Long = 0

    operator fun invoke(id: Long): Flow<UserModel?> {
        _id = id
//        return creatorProfileRepository.getCreatorDetails(id)
        return creatorProfileRepository.fetchCreatorDetails(id)
    }

    fun updateProfile(
        property: String,
        newValue: Any,
    ) {
        creatorProfileRepository.updateUserProfile(_id, property, newValue)
    }
}