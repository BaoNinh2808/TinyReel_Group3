package com.example.domain.following

import com.example.data.model.ContentCreatorFollowingModel
import com.example.data.repository.home.FollowingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContentCreatorsUseCase @Inject constructor(private val followingRepository: FollowingRepository) {
    operator fun invoke(): Flow<List<ContentCreatorFollowingModel>> {
        return followingRepository.getContentCreatorForFollowing()
    }
}