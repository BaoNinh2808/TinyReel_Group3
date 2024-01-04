package com.example.domain.foryou

import com.example.data.model.VideoModel
import com.example.data.repository.home.ForYouRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForYouPageFeedUseCase @Inject constructor(private val forYouRepository: ForYouRepository) {
    operator fun invoke(): Flow<List<VideoModel>> {
        return forYouRepository.getForYouPageFeeds()
    }
}