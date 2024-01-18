package com.example.composable

import android.content.Context
import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.extension.Space
import com.example.core.utils.IntentUtils.share
import com.example.data.model.VideoModel
import com.example.theme.*
import com.example.theme.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val databaseReference = FirebaseDatabase.getInstance().getReference()
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun TinyReelVerticalVideoPager(
    modifier: Modifier = Modifier,
    videos: List<VideoModel>,
    initialPage: Int? = 0,
    showUploadDate: Boolean = false,
    onclickComment: (videoId: String) -> Unit,
    onClickLike: (videoId: String, likeStatus: Boolean) -> Unit,
    onclickFavourite: (videoId: String,saveStatus: Boolean) -> Unit,
    onClickAudio: (VideoModel) -> Unit,
    onClickUser: (userId: Long) -> Unit,
//    onClickFavourite: (isFav: Boolean) -> Unit = {},
    onClickShare: (() -> Unit)? = null
) {
    val pagerState = rememberPagerState(initialPage = initialPage ?: 0, pageCount = {videos.size})
    val coroutineScope = rememberCoroutineScope()
    val localDensity = LocalDensity.current
    val context = LocalContext.current
    FirebaseApp.initializeApp(context)
    databaseReference.child("TinyReel").child("forYou").child("videos").setValue(videos)


    lateinit var database: DatabaseReference
    database = Firebase.database.reference
//    database.child("TinyReel").child("forYou").child("videos").setValue(videos)


    val fling = PagerDefaults.flingBehavior(
        state = pagerState, lowVelocityAnimationSpec = tween(
            easing = LinearEasing, durationMillis = 300
        )
    )

    VerticalPager(
        beyondBoundsPageCount = videos.size,
        state = pagerState,
        flingBehavior = fling,
        modifier = modifier
    ) {
        var pauseButtonVisibility by remember { mutableStateOf(false) }
        var doubleTapState by remember {
            mutableStateOf(
                Triple(
                    Offset.Unspecified, //offset
                    false, //double tap anim start
                    0f //rotation angle
                )
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            VideoPlayer(videos[it], pagerState, it, onSingleTap = {
                pauseButtonVisibility = it.isPlaying
                it.playWhenReady = !it.isPlaying
            },
                onDoubleTap = { exoPlayer, offset ->
                    coroutineScope.launch {
                        videos[it].currentViewerInteraction.isLikedByYou = true
                        val rotationAngle = (-10..10).random()
                        doubleTapState = Triple(offset, true, rotationAngle.toFloat())
                        delay(400)
                        doubleTapState = Triple(offset, false, rotationAngle.toFloat())
                    }
                },
                onVideoDispose = { pauseButtonVisibility = false },
                onVideoGoBackground = { pauseButtonVisibility = false }

            )


            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    FooterUi(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        item = videos[it],
                        showUploadDate=showUploadDate,
                        onClickAudio = onClickAudio,
                        onClickUser = onClickUser,
                    )

                    SideItems(
                        modifier = Modifier,
                        videos[it],
                        doubleTabState = doubleTapState,
                        onclickComment = onclickComment,
                        onClickUser = onClickUser,
                        onClickShare = onClickShare
                    )
                }
                12.dp.Space()
            }


            AnimatedVisibility(
                visible = pauseButtonVisibility,
                enter = scaleIn(spring(Spring.DampingRatioMediumBouncy), initialScale = 1.5f),
                exit = scaleOut(tween(150)),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(36.dp)
                )
            }

            val iconSize = 110.dp
            AnimatedVisibility(visible = doubleTapState.second,
                enter = scaleIn(spring(Spring.DampingRatioMediumBouncy), initialScale = 1.3f),
                exit = scaleOut(
                    tween(600), targetScale = 1.58f
                ) + fadeOut(tween(600)) + slideOutVertically(
                    tween(600)
                ),
                modifier = Modifier.run {
                    if (doubleTapState.first != Offset.Unspecified) {
                        this.offset(x = localDensity.run {
                            doubleTapState.first.x.toInt().toDp().plus(-iconSize.div(2))
                        }, y = localDensity.run {
                            doubleTapState.first.y.toInt().toDp().plus(-iconSize.div(2))
                        })
                    } else this
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null,
                    tint = if (doubleTapState.second) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.8f
                    ),
                    modifier = Modifier
                        .size(iconSize)
                        .rotate(doubleTapState.third)
                )
            }


        }
    }

}
@Composable
fun SideItems(
    modifier: Modifier,
    item: VideoModel,
    doubleTabState: Triple<Offset, Boolean, Float>,
    onclickComment: (videoId: String) -> Unit,
    onClickUser: (userId: Long) -> Unit,
    onClickShare: (() -> Unit)? = null,

//    onClickFavourite: (isFav: Boolean) -> Unit
) {

    val context = LocalContext.current
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {


        12.dp.Space()

        var isLiked by remember {
            mutableStateOf(item.currentViewerInteraction.isLikedByYou)
        }
        var isFavorited by remember {
            mutableStateOf(item.currentViewerInteraction.isFavoritedByYou)
        }
        var isCommented by remember {
            mutableStateOf(item.currentViewerInteraction.isCommentedByYou)
        }
        var isShared by remember {
            mutableStateOf(item.currentViewerInteraction.isSharedByYou)
        }

        LaunchedEffect(key1 = doubleTabState) {
            if (doubleTabState.first != Offset.Unspecified && doubleTabState.second) {
                isLiked = doubleTabState.second
                item.currentViewerInteraction.isLikedByYou = doubleTabState.second
                item.videoStats.like = (item.videoStats.like.toString().toInt() + 1).toLong()
                item.videoStats.formattedLikeCount = item.videoStats.like.toString()
                databaseReference.child("TinyReel").child("forYou").child("videos").child(item.videoId).child("videoStats").child("like").setValue(item.videoStats.like)
            }
        }

        LikeIconButton(isLiked = isLiked,
            likeCount = item.videoStats.formattedLikeCount,
            onLikedClicked = {
                isLiked = it
                item.currentViewerInteraction.isLikedByYou = it
                if (isLiked) {
                    item.videoStats.like = (item.videoStats.like.toString().toInt() + 1).toLong()
                    item.videoStats.formattedLikeCount = item.videoStats.like.toString()
                    databaseReference.child("TinyReel").child("forYou").child("videos").child(item.videoId).child("videoStats").child("like").setValue(item.videoStats.like)
                    //
                }
                else{
                    item.videoStats.like = (item.videoStats.like.toString().toInt() - 1).toLong()
                    item.videoStats.formattedLikeCount = item.videoStats.like.toString()
                    databaseReference.child("TinyReel").child("forYou").child("videos").child(item.videoId).child("videoStats").child("like").setValue(item.videoStats.like)
                }
            })

        CommentIconButton(isCommented = isCommented,
            commentCount = item.videoStats.formattedCommentCount,
            //onclickComment(item.videoId)
            onCommentClicked = {
                isCommented = it
                item.currentViewerInteraction.isCommentedByYou = it
                onclickComment(item.videoId)

            }
        )

        SaveIconButton(isFavorited= isFavorited,
            saveCount = item.videoStats.formattedFavouriteCount,
            onFavoritedClicked = {
                isFavorited = it
                item.currentViewerInteraction.isFavoritedByYou = it
                if (isFavorited) {
                    item.videoStats.favourite = (item.videoStats.favourite.toString().toInt() + 1).toLong()
                    item.videoStats.formattedFavouriteCount = item.videoStats.favourite.toString()
                    databaseReference.child("TinyReel").child("forYou").child("videos").child(item.videoId).child("videoStats").child("save").setValue(item.videoStats.favourite)
                }
                else{
                    item.videoStats.favourite = (item.videoStats.favourite.toString().toInt() - 1).toLong()
                    item.videoStats.formattedFavouriteCount = item.videoStats.favourite.toString()
                    databaseReference.child("TinyReel").child("forYou").child("videos").child(item.videoId).child("videoStats").child("save").setValue(item.videoStats.favourite)
                }
            }
        )

        ShareIconButton(isShared = isShared,
            shareCount = item.videoStats.formattedShareCount,
            onSharedClicked = {
                isShared = it
                item.currentViewerInteraction.isSharedByYou = it
                onClickShare?.let { onClickShare.invoke() } ?: run {
                    context.share(
                        //link video trên firebase
                        text = "https://firebasestorage.googleapis.com/v0/b/tinyreel-46587.appspot.com/o/${item.videoLink}?alt=media&token=8b9f9b9e-7b9a-4b7e-9b0a-9b9b9b9b9b9b"
                    )
                }
            }
        )
        RotatingAudioView(item.authorDetails.profilePic)
    }
}

@Composable
fun LikeIconButton(
    isLiked: Boolean, likeCount: String, onLikedClicked: (Boolean) -> Unit
) {

    val maxSize = 38.dp
    val iconSize by animateDpAsState(targetValue = if (isLiked) 33.dp else 32.dp,
        animationSpec = keyframes {
            durationMillis = 400
            24.dp.at(50)
            maxSize.at(190)
            26.dp.at(330)
            32.dp.at(400).with(FastOutLinearInEasing)
        }
    )

    Box(
        modifier = Modifier
            .size(maxSize)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onLikedClicked(!isLiked)
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_tiny_like),
            contentDescription = null,
            tint = if (isLiked) MaterialTheme.colorScheme.primary else Color.White,
            modifier = Modifier.size(iconSize)
        )
    }

    Text(text = likeCount, style = MaterialTheme.typography.labelMedium)
    16.dp.Space()
}
@Composable
fun SaveIconButton(
    isFavorited: Boolean, saveCount: String, onFavoritedClicked: (Boolean) -> Unit
) {

    val maxSize = 38.dp
    val iconSize by animateDpAsState(targetValue = if (isFavorited) 33.dp else 32.dp,
        animationSpec = keyframes {
            durationMillis = 400
            24.dp.at(50)
            maxSize.at(190)
            26.dp.at(330)
            32.dp.at(400).with(FastOutLinearInEasing)
        }
    )

    Box(
        modifier = Modifier
            .size(maxSize)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onFavoritedClicked(!isFavorited)
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_tiny_saved),
            contentDescription = null,
            tint = if (isFavorited) Color.Yellow else Color.White,
            modifier = Modifier.size(iconSize)
        )
    }

    Text(text = saveCount, style = MaterialTheme.typography.labelMedium)
    16.dp.Space()
}
@Composable
fun CommentIconButton(
    isCommented: Boolean, commentCount: String, onCommentClicked: (Boolean) -> Unit
) {

    val maxSize = 38.dp
    val iconSize by animateDpAsState(targetValue = if (isCommented) 33.dp else 32.dp,
        animationSpec = keyframes {
            durationMillis = 400
            24.dp.at(50)
            maxSize.at(190)
            26.dp.at(330)
            32.dp.at(400).with(FastOutLinearInEasing)
        }
    )

    Box(
        modifier = Modifier
            .size(maxSize)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onCommentClicked(!isCommented)
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.message_icon__3_),
            contentDescription = null,
            //set #0578FF if isCommented else Color.White
            tint = if (isCommented) Color(0xFFF0578FF) else Color.White,
            //uncomment do not change color of comment icon

            modifier = Modifier.size(iconSize)
        )
    }

    Text(text = commentCount, style = MaterialTheme.typography.labelMedium)
    16.dp.Space()
}
@Composable
fun ShareIconButton(
    isShared: Boolean, shareCount: String, onSharedClicked: (Boolean) -> Unit
) {

    val maxSize = 38.dp
    val iconSize by animateDpAsState(targetValue = if (isShared) 33.dp else 32.dp,
        animationSpec = keyframes {
            durationMillis = 400
            24.dp.at(50)
            maxSize.at(190)
            26.dp.at(330)
            32.dp.at(400).with(FastOutLinearInEasing)
        }
    )

    Box(
        modifier = Modifier
            .size(maxSize)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onSharedClicked(!isShared)
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_tiny_share),
            contentDescription = null,
            tint = if (isShared) Color.Green else Color.White,
            //uncomment do not change color of comment icon

            modifier = Modifier.size(iconSize)
        )
    }

    Text(text = shareCount, style = MaterialTheme.typography.labelMedium)
    16.dp.Space()
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FooterUi(
    modifier: Modifier,
    item: VideoModel,
    showUploadDate: Boolean,
    onClickAudio: (VideoModel) -> Unit,
    onClickUser: (userId: Long) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Bottom) {
        Row (verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = item.authorDetails.profilePic,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        BorderStroke(width = 1.dp, color = White), shape = CircleShape
                    )
                    .clip(shape = CircleShape)
                    .clickable {
                        onClickUser.invoke(item.authorDetails.userId)
                    },
                contentScale = ContentScale.Crop
            )
            12.dp.Space()

            Column(
                modifier= Modifier
                    .width(8.dp)
            ){}
            Column {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                    onClickUser(item.authorDetails.userId)
                }) {
                    Text(
                        text = item.authorDetails.fullName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    if (showUploadDate) {
                        Text(
                            text = " . ${item.createdAt} ago",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    }
                }

                androidx.compose.material3.Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(0.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Red,
                    ),
                ) {
                    Text(
                        style = MaterialTheme.typography.bodySmall,
                        text = stringResource(id = R.string.follow)
                    )
                }
            }
        }
        5.dp.Space()


        5.dp.Space()
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth(0.85f)
        )
        10.dp.Space()
        val audioInfo: String = item.audioModel?.run {
            "Original sound - ${audioAuthor.uniqueUserName} - ${audioAuthor.fullName}"
        }
            ?: item.run { "Original sound - ${item.authorDetails.uniqueUserName} - ${item.authorDetails.fullName}" }


        //button follow
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.clickable {
                onClickAudio(item)
            }

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = audioInfo,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .basicMarquee()
            )
        }
    }
}



@Composable
fun RotatingAudioView(img: String) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(animation = keyframes { durationMillis = 7000 })
    )

    Box(modifier = Modifier.rotate(angle)) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Gray20, Gray20, GrayLight, Gray20, Gray20,
                        )
                    ), shape = CircleShape
                )
                .size(46.dp), contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                model = img,
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }
    }
}