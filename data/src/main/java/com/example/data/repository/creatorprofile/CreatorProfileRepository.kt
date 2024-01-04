package com.example.data.repository.creatorprofile

import com.example.data.model.UserModel
import com.example.data.model.VideoModel
import com.example.data.source.UsersDataSource.fetchSpecificUser
import com.example.data.source.VideoDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CreatorProfileRepository @Inject constructor() {
    fun getCreatorDetails(id: Long): Flow<UserModel?> {
        return fetchSpecificUser(id)
    }

    fun getCreatorPublicVideo(id: Long): Flow<List<VideoModel>> {
        return VideoDataSource.fetchVideosOfParticularUser(id)
    }
}