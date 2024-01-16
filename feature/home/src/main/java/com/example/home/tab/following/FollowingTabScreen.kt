package com.example.home.tab.following

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.DestinationRoute.CREATOR_PROFILE_ROUTE
import com.example.core.extension.Space
import com.example.data.model.ContentCreatorFollowingModel
import com.example.data.repository.home.FollowingRepository
import com.example.domain.following.GetContentCreatorsUseCase
import com.example.home.tab.following.component.CreatorCard
import com.example.theme.DarkBlue
import com.example.theme.R
import com.example.theme.SubTextColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FollowingScreen(
    navController: NavController,
    parentPagerState: PagerState,
//    viewModel: FollowingViewModel = hiltViewModel()
    viewModel: FollowingViewModel= FollowingViewModel(
        getContentCreatorsUseCase = GetContentCreatorsUseCase(
            followingRepository = FollowingRepository()
        )
    )
) {
    val viewState by viewModel.viewState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        80.dp.Space()
        Text(
            text = stringResource(id = R.string.trending_creators),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        10.dp.Space()
        Text(
            text = stringResource(id = R.string.follow_and_account_to_see),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            textAlign = TextAlign.Center,
            color = SubTextColor
        )
        22.dp.Space()

        if (parentPagerState.settledPage == 0) {
            viewState?.contentCreators?.let {
                VideoItem(
                    creatorList = it,
                    onclickUser = { userId -> navController.navigate("$CREATOR_PROFILE_ROUTE/$userId") })
            }
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoItem(
    creatorList: List<ContentCreatorFollowingModel>,
    onclickUser: (userId: Long) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {creatorList.size})
    HorizontalPager(
        //pageCount = creatorList.size,
        contentPadding = PaddingValues(horizontal = 54.dp),
        state = pagerState,
        beyondBoundsPageCount = 1
    ) {
        CreatorCard(
            page = it,
            pagerState = pagerState,
            item = creatorList[it],
            onClickUser = onclickUser,
            onClickFollow = {}
        )
    }
}

