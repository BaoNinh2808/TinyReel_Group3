package com.example.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.home.tab.following.FollowingScreen
import com.example.home.tab.foryou.ForYouTabScreen
import com.example.theme.R
import com.example.theme.TinyReelTheme
import com.example.theme.White
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val tabItems = arrayListOf(R.string.following, R.string.for_you)
    val pagerState = rememberPagerState(initialPage = 1, initialPageOffsetFraction=0f,pageCount = {tabItems.size})
    val coroutineScope = rememberCoroutineScope()

    TinyReelTheme(darkTheme = true) {
        Box(modifier = Modifier.fillMaxSize()) {

            HorizontalPager(
                state = pagerState,
            ) {
                when (it) {
                    0 -> FollowingScreen(navController, pagerState)
                    1 -> ForYouTabScreen(navController)
                }
            }

            val edge = LocalConfiguration.current.screenWidthDp.dp.div(2).minus(100.dp)
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.Transparent,
                divider = {},
                modifier = Modifier.padding(top = 8.dp),
                indicator = { tabPositions ->
                    val modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .padding(horizontal = 38.dp)

                    TabRowDefaults.Indicator(modifier, color = White)
//                    TabRowDefaults.SecondaryIndicator(
//                        modifier, color = White
//                    )
                },
                edgePadding = edge
            ) {

                tabItems.forEachIndexed { index, item ->
                    val isSelected = pagerState.currentPage == index
                    Tab(
                        selected = isSelected,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            val textStyle =
                                if (isSelected) MaterialTheme.typography.titleMedium.merge(
                                    TextStyle(color = Color.White)
                                )
                                else TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 17.sp,
                                    color = White.copy(alpha = 0.6f)
                                )
                            Text(text = stringResource(id = item), style = textStyle)
                        }
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewHomeScreen(){
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}