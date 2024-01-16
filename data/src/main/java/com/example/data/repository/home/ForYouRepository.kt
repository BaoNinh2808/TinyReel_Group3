package com.example.data.repository.home

import com.example.data.model.VideoModel
import com.example.data.source.VideoDataSource.fetchVideos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForYouRepository @Inject constructor() {
    fun getForYouPageFeeds(): Flow<List<VideoModel>> {
        return fetchVideos()
    }
}