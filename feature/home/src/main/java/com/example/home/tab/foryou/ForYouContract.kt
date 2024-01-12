package com.example.home.tab.foryou

import com.example.data.model.VideoModel

data class ViewState(
    val forYouPageFeed: List<VideoModel>? = null,
    val isLoading: Boolean? = null,
    val error: String? = null,
)

sealed class ForYouEvent {
}
