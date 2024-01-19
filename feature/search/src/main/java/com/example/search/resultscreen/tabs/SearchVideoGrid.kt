package com.example.search.resultscreen.tabs

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.core.utils.FileUtils
import com.example.data.model.UserModel
import com.example.data.model.VideoModel
import com.example.theme.GrayMainColor
import com.example.theme.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun SearchVideoGrid(
    videoList: List<VideoModel>,
    onClickVideo: (video: VideoModel, index: Int) -> Unit,
    isBackList : Boolean = false){
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            if (!isBackList){
                itemsIndexed(videoList) { index, item ->
                    context.VideoGridItem(item, index, onClickVideo = onClickVideo)
                }
            }
        }
    )
}


@Composable
fun Context.VideoGridItem(item: VideoModel, index: Int, onClickVideo: (VideoModel, Int) -> Unit) {

    Box(modifier = Modifier
        .padding(8.dp)
        .height(300.dp)) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onClickVideo(item, index)
                    }
            ) {
//                var thumbnail: Bitmap? by remember {
//                    mutableStateOf(null)
//                }
                var thumbnail by remember {
                    mutableStateOf<Pair<Bitmap?, Boolean>>(Pair(null, true))  //bitmap, isShow
                }
                AsyncImage(
                    model = thumbnail.first,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayMainColor)
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.5f)
                                    ), startY = 300f
                                )
                            )
                        },
                    contentScale = ContentScale.Crop
                )
                LaunchedEffect(key1 = true) {
                    withContext(Dispatchers.IO) {
                        val bm = FileUtils.extractThumbnail2("https://firebasestorage.googleapis.com/v0/b/tinyreel-8d37c.appspot.com/o/${item.videoLink}?alt=media&token=8b9f9b9e-7b9a-4b7e-9b0a-9b9b9b9b9b9b", 0)
                        withContext(Dispatchers.Main) {
                            thumbnail = thumbnail.copy(first = bm, second = thumbnail.second)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = item.videoStats.formattedViewsCount,
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Text(
                text = item.description,
                maxLines = 2,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchResultScreen(){
//    SearchResultScreen(navController = rememberNavController())
    //I want to create an instance of VideoModel
    val videoModel = VideoModel(
        videoId = "kylie_vid1",
        videoLink = "kylie_vid1.mp4",
        authorDetails = UserModel(),
        videoStats = VideoModel.VideoStats(
            like = 409876,
            comment = 8356,
            share = 3000,
            favourite = 1500
        ),
    )
    val videoList = listOf(videoModel, videoModel, videoModel, videoModel, videoModel, videoModel , videoModel, videoModel, videoModel, videoModel)
    SearchVideoGrid(videoList = videoList, onClickVideo = { video, index -> })
}