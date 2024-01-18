package com.example.domain.creatorprofile

import android.util.Log
import com.example.data.model.VideoModel
import com.example.data.repository.creatorprofile.CreatorProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCreatorPublicVideoUseCase @Inject constructor(
    private val creatorProfileRepository: CreatorProfileRepository
) {
    operator fun invoke(id: Long): Flow<List<VideoModel>> {

//        return creatorProfileRepository.getCreatorPublicVideo(id)
        return CreatorProfileRepository.getQueryResult(creatorProfileRepository, id)
    }
}