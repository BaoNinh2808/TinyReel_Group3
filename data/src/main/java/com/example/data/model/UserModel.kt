package com.example.data.model

import com.example.core.extension.formattedCount

data class UserModel(
//    val userId: Long,
//    val uniqueUserName: String,
//    val fullName: String,
//    val following: Long,
//    val followers: Long,
//    val likes: Long,
//    val bio: String,
//    val profilePic: String,
//    val isVerified: Boolean,
//    val isLikedVideoPrivate: Boolean = true,
//    val pinSocialMedia: SocialMedia? = null
    val userId: Long = 0,
    var uniqueUserName: String = "",
    val fullName: String = "",
    val following: Long = 0,
    val followers: Long = 0,
    val likes: Long = 0,
    var bio: String = "",
    var profilePic: String = "",
    val isVerified: Boolean = false,
    val isLikedVideoPrivate: Boolean = true,
    val pinSocialMedia: SocialMedia? = null
) {
    var formattedFollowingCount: String = ""
    var formattedFollowersCount: String = ""
    var formattedLikeCount: String = ""

    init {
        formattedLikeCount = likes.formattedCount()
        formattedFollowingCount = following.formattedCount()
        formattedFollowersCount = followers.formattedCount()
    }

    data class SocialMedia(
        val type:  SocialMediaType = SocialMediaType.INSTAGRAM,
        val link: String = ""
    )
}

enum class SocialMediaType {
    INSTAGRAM,
    YOUTUBE
}