package com.example.search.searchscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composable.TopBar
import com.example.core.DestinationRoute.SEARCH_RESULT_ROUTE
import com.example.data.repository.search.SearchRepository
import com.example.domain.search.SearchUseCase
import com.example.theme.R
import com.example.theme.Typography
import com.example.theme.WhiteLightDimBg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel : SearchScreenViewModel = hiltViewModel()){

    var text by remember{mutableStateOf("")}
    var active by remember{mutableStateOf(false)}

//    val scrollState = rememberScrollState()


    val oldSearchQuery by viewModel.oldSearchQuery.collectAsState()

    val hotSearchQuery by viewModel.hotSearchQuery.collectAsState()

    //create a search screen
    Scaffold (
        topBar = {
            //create a top bar
            TopBar(title = stringResource(id = R.string.search)){ navController.navigateUp() }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            //create a search bar
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = { text = it },
                onSearch = {
//                    oldSearchQuery.add(text)
                    viewModel.updateSearchQuery(1, text)
                    navController.navigate("searchResult/$text")
                    active = false
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.search))
                },
                leadingIcon = {
                    //create a leading icon
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    //create a trailing icon
                    if (active){
                        Icon(modifier = Modifier.clickable{
                            if (text.isNotEmpty()){
                                text = ""
                            } else {
                                active = false
                            }
                        },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                        )
                    }
                },
                colors = SearchBarDefaults.colors(
                    containerColor = WhiteLightDimBg,
                    dividerColor = WhiteLightDimBg,
                )
            ) {
                //display when search bar is active (when user click on search bar)
                displayHistoryAndHotSearchQuery(oldSearchQuery = oldSearchQuery, hotSearchQuery = hotSearchQuery, navController = navController)
            }
            //display when search bar is not active
            displayHistoryAndHotSearchQuery(oldSearchQuery = oldSearchQuery, hotSearchQuery = hotSearchQuery, navController = navController)
        }
    }
}
@Composable
fun displayHistoryAndHotSearchQuery(
    oldSearchQuery: List<String>,
    hotSearchQuery: List<String>,
    navController: NavController
){
    // create 2 list of search query: old search query and hot search query (from api)
    // create a row for each search query
    oldSearchQuery.forEach {
        // pass the first item of old search query to search bar
        Row(modifier = Modifier.padding(all = 16.dp)) {
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = Icons.Default.History,
                contentDescription = "History Icon")
            Text(text = it, modifier = Modifier.clickable {
                navController.navigate("searchResult/$it")
            })
        }
    }

    //Text for represent hot search query

    Text(
        modifier = Modifier.padding(all = 16.dp),
        text = stringResource(id = R.string.hot_search_query),
        style = Typography.displayMedium
    )

    hotSearchQuery.forEach {
        Row(modifier = Modifier.padding(all = 16.dp)) {
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = Icons.Default.Whatshot,
                contentDescription = "History Icon")
            Text(text = it, modifier = Modifier.clickable {
                navController.navigate("searchResult/$it")
            })
        }
    }
}

//preview
@Composable
@Preview
fun SearchScreenPreview(){
    SearchScreen(navController = rememberNavController(),
        viewModel = SearchScreenViewModel(
            searchUseCase = SearchUseCase(
                searchRepository = SearchRepository()
            ))
    )
}


