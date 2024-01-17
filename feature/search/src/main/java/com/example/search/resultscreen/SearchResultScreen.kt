package com.example.search.resultscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.repository.search.SearchRepository
import com.example.data.repository.search.SearchResultRepository
import com.example.domain.search.SearchResultUseCase
import com.example.domain.search.SearchUseCase
import com.example.search.resultscreen.tabs.SearchVideoGrid
import com.example.search.searchscreen.SearchScreen
import com.example.search.searchscreen.SearchScreenViewModel


@Composable
fun SearchResultScreen(
    navController: NavController,
    viewModel: SearchResultViewModel = hiltViewModel(),
    query : String){
    //chỉ cần truyền 1 list các video vào trong SearchVideoGrid là hiển thị được
    val resultVideos by viewModel.getResultVideoList(query).collectAsState()
    SearchVideoGrid(videoList = resultVideos, onClickVideo = { video, index -> })
}

@Preview
@Composable
fun SearchResultScreenPreview(){
    SearchResultScreen(navController = rememberNavController(),
        viewModel = SearchResultViewModel(
            searchResultUseCase = SearchResultUseCase(
                searchResultRepository = SearchResultRepository()
            )
        ),
        query = "aaaa"
    )
}
