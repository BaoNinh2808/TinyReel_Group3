package com.example.myprofile.myprofilevideo

import androidx.compose.foundation.ScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.creatorprofile.component.VideoGrid
import com.example.creatorprofile.screen.creatorprofile.CreatorProfileViewModel
import com.example.data.model.VideoModel
import com.example.myprofile.MyProfileViewModel

@Composable
fun PublicVideoTab(
    viewModel: MyProfileViewModel,
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