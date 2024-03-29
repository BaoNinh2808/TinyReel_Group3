package com.example.data.model

import com.example.core.extension.formattedCount
import com.example.core.extension.randomUploadDate

data class VideoModel(
//    val videoId: String,
//    val authorDetails: UserModel,
//    val videoStats: VideoStats,
//    val videoLink: String,
//    val description: String,
//    val currentViewerInteraction: ViewerInteraction = ViewerInteraction(),
//    val createdAt: String = randomUploadDate(),
//    val audioModel: AudioModel? = null,
//    val hasTag: List<HasTag> = listOf(),
    val videoId: String = "",
    val authorDetails: UserModel = UserModel(),
    val videoStats: VideoStats = VideoStats(),
    val videoLink: String = "",
    val description: String = "",
    val currentViewerInteraction: ViewerInteraction = ViewerInteraction(),
    val createdAt: String = randomUploadDate(),
    val audioModel: AudioModel? = null,
    val hasTag: List<HasTag> = listOf(),
) {
    data class VideoStats(
        var like: Long = 0,
        var comment: Long = 0,
        var share: Long = 0,
        var favourite: Long = 0,
        var views: Long = (like + 500..like + 100000).random()
    ) {
        var formattedLikeCount: String = ""
        var formattedCommentCount: String = ""
        var formattedShareCount: String = ""
        var formattedFavouriteCount: String = ""
        var formattedViewsCount: String = ""

        init {
            formattedLikeCount = like.formattedCount()
            formattedCommentCount = comment.formattedCount()
            formattedShareCount = share.formattedCount()
            formattedFavouriteCount = favourite.formattedCount()
            formattedViewsCount = views.formattedCount()
        }
    }

    data class HasTag(
        val id: Long,
        val title: String
    )

    data class ViewerInteraction(
        var isLikedByYou: Boolean = false,
        var isFavoritedByYou: Boolean = false,
        var isCommentedByYou: Boolean = false,
        var isSharedByYou: Boolean = false,
        var isAddedToFavourite: Boolean = false
    )
}