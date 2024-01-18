package com.example.domain.creatorprofile

import android.util.Log
import com.example.data.model.VideoModel
import com.example.data.repository.creatorprofile.CreatorProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCreatorPublicVideoUseCase @Inject constructor(
    private val creatorProfileRepository: CreatorProfileRepository
) {
    operator fun invoke(id: Long): Flow<List<VideoModel>> {
        return CreatorProfileRepository.getQueryResult(creatorProfileRepository, id)
    }
}