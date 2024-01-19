package com.example.myprofile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import coil.compose.AsyncImage
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.theme.R
import com.example.core.DestinationRoute
import com.example.composable.TopBar
import com.example.theme.SubTextColor
import com.example.composable.CustomButton
import com.example.core.utils.IntentUtils.redirectToApp
import com.example.data.model.SocialMediaType
import com.example.core.AppContract.Type.INSTAGRAM
import com.example.core.AppContract.Type.YOUTUBE
import com.example.theme.Gray

import com.example.data.model.VideoModel
import com.example.data.source.UsersDataSource.kylieJenner
import com.example.myprofile.myprofilevideo.VideoListingPager
import com.google.firebase.Firebase
import com.tinyreel.authentication.data.AuthRepositoryImpl
import com.tinyreel.authentication.di.AppModule

@Composable
fun MyProfileScreen(
    navController: NavController,
    viewModel: MyProfileViewModel = hiltViewModel()
) {
    val firebaseAuth = AppModule.provideFirebaseAuth()
    val authRepository = AppModule.providesAuthRepository(AuthRepositoryImpl(firebaseAuth))
    val currentUser = authRepository.currentUser
    viewModel.setAid(currentUser?.uid?:"")

    Log.d("TAG", "avadakedavra ${currentUser}")

//    val userInfoRef = db.collection("userInfo").whereEqualTo("uid", "123").get()
//        .addOnSuccessListener { querySnapshot ->
//            loggedIn = true
//            if (!querySnapshot.isEmpty) {
//                userName = querySnapshot.documents[0].getString("userName").toString()
//            }
//            else {
//                userName = "failed to fetch"
//            }
//        }
//        .addOnFailureListener { exception ->
//            loggedIn = true
//        }

//    val viewModel = MyProfileViewModel(
//        userId = 123,
//        getCreatorProfileUseCase = EditableCreatorProfileUseCase(CreatorProfileRepository()),
//        getCreatorPublicVideoUseCase = GetCreatorPublicVideoUseCase(CreatorProfileRepository())
//    )
//    val viewState by viewModel.viewState.collectAsState()

//    val viewModel = MyProfileViewModel(
//        9L,
//        GetCreatorProfileUseCase(
//            CreatorProfileRepository()
//        ),
//        GetCreatorPublicVideoUseCase(
//            CreatorProfileRepository()
//        )
//    )
    viewModel.setId(9L)

    Scaffold(topBar = {
        TopBar(
            navIcon = null,
            title = stringResource(id = R.string.profile),
            actions = {
                IconButton(onClick = {
                    navController.navigate(DestinationRoute.SETTING_ROUTE)
                }) {
                    Icon(painterResource(id = R.drawable.ic_hamburger), contentDescription = null)
                }
            }
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (currentUser == null) {
                UnAuthorizedInboxScreen {
                    navController.navigate(DestinationRoute.AUTHENTICATION_ROUTE)
                }
            }
            else {
                LoggedInProfileScreen(
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun UnAuthorizedInboxScreen(onClickSignup: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null,
            modifier = Modifier.size(68.dp)
        )
        Text(
            text = stringResource(id = R.string.sign_up_for_an_account),
            color = SubTextColor
        )
        CustomButton(
            buttonText = stringResource(id = R.string.sign_up),
            modifier = Modifier
                .fillMaxWidth(0.66f)
        ) {
            onClickSignup()
        }
    }
}

@Composable
fun LoggedInProfileScreen(
    navController: NavController,
    viewModel: MyProfileViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val viewState by viewModel.getCreatorPublicVideo().collectAsState()

    Column {
        BoxWithConstraints {
            val height = this.maxHeight

            Column(
                modifier = Modifier
//                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                ProfileDetails(navController, viewState)
                VideoListingPager(
                    scrollState = scrollState,
                    height = height,
                    viewModel = viewModel,
                    onClickVideo = { video, index ->
                        navController.navigate("${DestinationRoute.CREATOR_VIDEO_ROUTE}/${viewModel.userId}/$index")
                    }
                )
            }
        }
    }
}

@Composable
fun ColumnScope.ProfileDetails(
    navController: NavController,
    viewState: List<VideoModel>
//    userModel: UserModel
) {
    var userModel = kylieJenner
    if (viewState.size > 0) {
        userModel = viewState[0].authorDetails
    }
    val context = LocalContext.current

    AsyncImage(
        model = userModel.profilePic ?: R.drawable.ic_profile,
        contentDescription = null,
        modifier = Modifier
            .size(94.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "@${userModel.uniqueUserName?:""}",
            style = MaterialTheme.typography.bodyMedium
        )
        if (userModel.isVerified == true) {
            Icon(
                painter = painterResource(id = R.drawable.ic_verified),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (
            followingCount, tvFollowing, separator1,
            followersCount, tvFollowers, separator2, likeCount, tvLike,
        ) = createRefs()


        Text(
            text = userModel.formattedFollowingCount ?: "-",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(followingCount) {
                top.linkTo(parent.top)
                centerHorizontallyTo(tvFollowing)
            }
        )
        Text(
            text = stringResource(id = R.string.following),
            color = SubTextColor,
            modifier = Modifier.constrainAs(tvFollowing) {
                top.linkTo(followingCount.bottom, margin = 3.dp)
                start.linkTo(parent.start, margin = 36.dp)
                end.linkTo(separator1.start)
            }
        )

        Divider(modifier = Modifier
            .width(1.dp)
            .height(16.dp)
            .constrainAs(separator1) {
                start.linkTo(tvFollowing.end)
                end.linkTo(tvFollowers.start)
                bottom.linkTo(tvFollowing.bottom)
                top.linkTo(followingCount.top)
            })


        Text(
            text = userModel.formattedFollowersCount ?: "-",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(followersCount) {
                top.linkTo(followingCount.top)
                centerHorizontallyTo(tvFollowers)
            }
        )

        Text(
            text = stringResource(id = R.string.followers),
            color = SubTextColor,
            modifier = Modifier.constrainAs(tvFollowers) {
                start.linkTo(separator1.end)
                end.linkTo(separator2.start)
                top.linkTo(followersCount.bottom, margin = 3.dp)
            }
        )


        Divider(modifier = Modifier
            .width(1.dp)
            .height(16.dp)
            .constrainAs(separator2) {
                start.linkTo(tvFollowers.end)
                end.linkTo(tvLike.start)
                top.linkTo(followingCount.top)
                bottom.linkTo(tvFollowers.bottom)
            })

        Text(
            text = userModel.formattedLikeCount ?: "-",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(likeCount) {
                top.linkTo(followingCount.top)
                centerHorizontallyTo(tvLike)
            }
        )

        Text(
            text = stringResource(id = R.string.likes),
            color = SubTextColor,
            modifier = Modifier.constrainAs(tvLike) {
                top.linkTo(likeCount.bottom, margin = 3.dp)
                start.linkTo(separator2.end)
                end.linkTo(parent.end, margin = 36.dp)
            }
        )
    }

    Row(
        modifier = Modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { }, modifier = Modifier
                .width(158.dp)
                .height(42.dp), shape = RoundedCornerShape(2.dp)
        ) {
            Text(text = stringResource(id = R.string.follow))
        }
        userModel.pinSocialMedia?.let {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(2.dp),
                        color = Gray.copy(alpha = 0.2f)
                    )
                    .clickable {
                        context.redirectToApp(
                            link = it.link,
                            type = when (it.type) {
                                SocialMediaType.INSTAGRAM -> INSTAGRAM
                                SocialMediaType.YOUTUBE -> YOUTUBE
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                val icon = when (it.type) {
                    SocialMediaType.INSTAGRAM -> R.drawable.ic_instagram
                    SocialMediaType.YOUTUBE -> R.drawable.ic_youtube
                }
                Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color.Unspecified)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(40.dp)
                .border(
                    width = 1.dp, shape = RoundedCornerShape(2.dp), color = Gray.copy(alpha = 0.2f)
                )
                .clickable { navController.navigate(DestinationRoute.MY_PROFILE_SETTING_ROUTE) },
            contentAlignment = Alignment.Center,
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = null, tint = Color.Black)
        }
    }

    Text(
        text = userModel.bio ?: stringResource(id = R.string.no_bio_yet),
    )
}