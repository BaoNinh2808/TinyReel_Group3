package com.example.search.resultscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composable.TopBar
import com.example.data.model.VideoModel
import com.example.data.repository.search.SearchRepository
import com.example.data.repository.search.SearchResultRepository
import com.example.domain.search.SearchResultUseCase
import com.example.domain.search.SearchUseCase
import com.example.search.resultscreen.tabs.SearchVideoGrid
import com.example.search.searchscreen.SearchScreen
import com.example.search.searchscreen.SearchScreenViewModel
import com.example.theme.R
import java.text.Normalizer
import java.util.regex.Pattern

@Composable
fun SearchResultScreen(
    navController: NavController, navBackStackEntry: NavBackStackEntry,
    viewModel: SearchResultViewModel = hiltViewModel()) {

    val query = navBackStackEntry.arguments?.getString("query") ?: ""
    //chỉ cần truyền 1 list các video vào trong SearchVideoGrid là hiển thị được
    val resultVideos by viewModel.getResultVideoList(query).collectAsState()

    val blackList = listOf<String>("au dam", "au dam", "hiep dam", "hiepdam", "bandam", "ban tre em", "bantreem", "fulro", "giet nguoi")
    var isBlackList = false

    val normalizedQuery = com.example.data.repository.search.normalizeString(query)

    for (word in blackList){
        val normalizedDescription = com.example.data.repository.search.normalizeString(word)
        if (normalizedQuery.contains(normalizedDescription, ignoreCase = true)) {
            isBlackList = true
        }
    }

    Scaffold(
        topBar = {
            //create a top bar
            TopBar(title = stringResource(id = R.string.search_result)) { navController.navigateUp() }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center // Căn giữa nội dung trong Box
        ) {
            if (isBlackList == true) {
                Text(text = "Vui lòng không tìm kiếm các từ khóa nhạy cảm")
            }
            else if (resultVideos.isEmpty()) {
                Text(text = "Không tìm thấy kết quả")
            }
        else{
                SearchVideoGrid(videoList = resultVideos, onClickVideo = { video, index -> })
            }

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
