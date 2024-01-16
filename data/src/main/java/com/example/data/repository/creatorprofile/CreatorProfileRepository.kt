package com.example.data.repository.creatorprofile

import com.example.data.model.UserModel
import com.example.data.model.VideoModel
import com.example.data.source.UsersDataSource
import com.example.data.source.VideoDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CreatorProfileRepository @Inject constructor() {
    fun getCreatorDetails(id: Long): Flow<UserModel?> {
        return UsersDataSource.fetchSpecificUser(id)
    }

    fun updateUserProfile(
        id: Long,
        property: String,
        newValue: Any,
    ) {
        when (property) {
            "username" -> UsersDataSource.setUniqueUserName(id, newValue.toString())
            "bio" -> UsersDataSource.setBio(id, newValue.toString())
            "avatar" -> UsersDataSource.setProfilePic(id, newValue.toString())
        }
    }

    fun getCreatorPublicVideo(id: Long): Flow<List<VideoModel>> {
        return VideoDataSource.fetchVideosOfParticularUser(id)
    }
}