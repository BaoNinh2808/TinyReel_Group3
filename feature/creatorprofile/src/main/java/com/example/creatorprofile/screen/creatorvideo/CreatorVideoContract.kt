package com.example.creatorprofile.screen.creatorvideo

import com.example.data.model.VideoModel

data class ViewState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val creatorVideosList: List<VideoModel>? = null
)

sealed class CreatorVideoEvent {
}