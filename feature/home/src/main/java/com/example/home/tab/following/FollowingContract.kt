package com.example.home.tab.following

import com.example.data.model.ContentCreatorFollowingModel

data class ViewState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val contentCreators: List<ContentCreatorFollowingModel>? = null
)

sealed class FollowingEvent {
}