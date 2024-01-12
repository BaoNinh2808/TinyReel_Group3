package com.example.data.repository.home

import com.example.data.model.ContentCreatorFollowingModel
import com.example.data.source.ContentCreatorForFollowingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FollowingRepository @Inject constructor() {
    fun getContentCreatorForFollowing(): Flow<List<ContentCreatorFollowingModel>> {
        return ContentCreatorForFollowingDataSource.fetchContentCreatorForFollowing()
    }
}
