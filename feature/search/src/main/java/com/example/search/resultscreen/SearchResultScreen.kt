package com.example.search.resultscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composable.TopBar
import com.example.data.repository.search.SearchRepository
import com.example.data.repository.search.SearchResultRepository
import com.example.domain.search.SearchResultUseCase
import com.example.domain.search.SearchUseCase
import com.example.search.resultscreen.tabs.SearchVideoGrid
import com.example.search.searchscreen.SearchScreen
import com.example.search.searchscreen.SearchScreenViewModel
import com.example.theme.R


@Composable
fun SearchResultScreen(
    navController: NavController, navBackStackEntry: NavBackStackEntry,
    viewModel: SearchResultViewModel = hiltViewModel()) {

    val query = navBackStackEntry.arguments?.getString("query") ?: ""
    //chỉ cần truyền 1 list các video vào trong SearchVideoGrid là hiển thị được
    val resultVideos by viewModel.getResultVideoList(query).collectAsState()

    Scaffold(
        topBar = {
            //create a top bar
            TopBar(title = stringResource(id = R.string.search_result)) { navController.navigateUp() }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SearchVideoGrid(videoList = resultVideos, onClickVideo = { video, index -> })
        }
    }
}

//@Preview
//@Composable
//fun SearchResultScreenPreview(){
//    SearchResultScreen(navController = rememberNavController(),
//        viewModel = SearchResultViewModel(
//            searchResultUseCase = SearchResultUseCase(
//                searchResultRepository = SearchResultRepository()
//            )
//        ),
//        query = "aaaa"
//    )
//}
