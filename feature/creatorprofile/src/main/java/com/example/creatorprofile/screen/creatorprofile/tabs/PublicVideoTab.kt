package com.example.creatorprofile.screen.creatorprofile.tabs

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.creatorprofile.component.VideoGrid
import com.example.creatorprofile.screen.creatorprofile.CreatorProfileViewModel
import com.example.data.model.VideoModel

@Composable
fun PublicVideoTab(
    viewModel: CreatorProfileViewModel = hiltViewModel(),
    scrollState: ScrollState,
    onClickVideo: (video: VideoModel, index: Int) -> Unit
) {
    val creatorPublicVideos by viewModel.publicVideosList.collectAsState()
    VideoGrid(
        scrollState = scrollState,
        videoList = creatorPublicVideos,
        onClickVideo = onClickVideo
    )
}